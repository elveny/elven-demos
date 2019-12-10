/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author elven
 * @Filename ProducerAndCustomer1.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/15 22:25</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ProducerAndCustomer2 {

    // 仓库
    static class Depot {
        // 仓库的容量
        private int capacity;
        // 仓库的实际数量
        private int size;

        private Lock lock = new ReentrantLock();
        private Condition fullCondition = lock.newCondition();
        private Condition emptyCondition = lock.newCondition();

        public Depot(int capacity) {
            this.capacity = capacity;
            this.size = 0;
        }

        public void produce(int val) {
            lock.lock();
            try {
                // left 表示“想要生产的数量”(有可能生产量太多，需多次生产)
                int left = val;
                while (left > 0) {
                    // 库存已满时，等待“消费者”消费产品。
                    while (size >= capacity) {
                        fullCondition.await();
                    }
                    // 获取“实际生产的数量”(即库存中新增的数量)
                    // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                    // 否则“实际增量”=“想要生产的数量”
                    int inc = (size + left) > capacity ? (capacity - size) : left;
                    size += inc;
                    System.out.printf("%s produce(%3d) --> old-left=%3d, ",Thread.currentThread().getName(), val, left);
                    left -= inc;
                    System.out.printf("produce=%3d, size=%3d, new-left=%3d\n", inc, size, left);
                    // 通知“消费者”可以消费了。
                    emptyCondition.signal();
                }
            } catch (InterruptedException e) {
            }
            finally {
                lock.unlock();
            }
        }

        public synchronized void consume(int val) {
            lock.lock();
            try {
                // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
                int left = val;
                while (left > 0) {
                    // 库存为0时，等待“生产者”生产产品。
                    while (size <= 0) {
                        emptyCondition.await();
                    }
                    // 获取“实际消费的数量”(即库存中实际减少的数量)
                    // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                    // 否则，“实际消费量”=“客户要消费的数量”。
                    int dec = (size < left) ? size : left;
                    size -= dec;
                    System.out.printf("%s consume(%3d) <-- old-left=%3d, ", Thread.currentThread().getName(), val, left);
                    left -= dec;
                    System.out.printf("consume=%3d, size=%3d, new-left=%3d\n", dec, size, left);
                    fullCondition.signal();
                }
            } catch (InterruptedException e) {
            }
            finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "capacity:" + capacity + ", actual size:" + size;
        }
    }

    // 生产者
    static class Producer {
        private Depot depot;

        public Producer(Depot depot) {
            this.depot = depot;
        }

        // 消费产品：新建一个线程向仓库中生产产品。
        public void produce(final int val) {
            new Thread(() -> depot.produce(val)).start();
        }
    }

    // 消费者
    static class Customer {
        private Depot depot;

        public Customer(Depot depot) {
            this.depot = depot;
        }

        // 消费产品：新建一个线程从仓库中消费产品。
        public void consume(final int val) {
            new Thread(() -> depot.consume(val)).start();
        }
    }

    public static void main(String[] args) {
        Depot mDepot = new Depot(100);
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }

}