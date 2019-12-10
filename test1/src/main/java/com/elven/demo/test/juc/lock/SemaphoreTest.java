/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author elven
 * @Filename SemaphoreTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/15 23:50</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        new MyThread(semaphore, 7).start();
        new MyThread(semaphore, 3).start();
        new MyThread(semaphore, 5).start();
        new MyThread(semaphore, 9).start();
    }

    static class MyThread extends Thread {
        private Semaphore semaphore;
        private int count;

        public MyThread(Semaphore semaphore, int count) {
            this.semaphore = semaphore;
            this.count = count;
        }

        @Override
        public void run() {

            try {
                semaphore.acquire(count);
                System.out.println(Thread.currentThread().getName() + " acquire count:" + count);
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放给定数目的许可，将其返回到信号量。
                semaphore.release(count);
                System.out.println(Thread.currentThread().getName() + " release " + count );
            }
        }
    }
}