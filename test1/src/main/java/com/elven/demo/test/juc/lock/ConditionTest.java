/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Filename ConditionTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 2019/10/15 22:13</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ConditionTest {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" start myThread");

            myThread.start();
            System.out.println(Thread.currentThread().getName()+" block");
            condition.await();

            System.out.println(Thread.currentThread().getName()+" continue");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " wake up main");
                condition.signal();
            }
            finally {
                lock.unlock();
            }

        }
    }
}

