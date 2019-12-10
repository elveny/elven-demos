/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Filename CountDownLatchTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 2019/10/15 23:35</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println(Thread.currentThread().getName()+" start");
        for (int i = 0; i < 5; i++) {
            new MyThread(countDownLatch).start();
        }
        System.out.println(Thread.currentThread().getName()+" wait");
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class MyThread extends Thread{
        private CountDownLatch countDownLatch;

        public MyThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));

                System.out.println(Thread.currentThread().getName() + " countDownLatch.count:"+countDownLatch.getCount());

                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}