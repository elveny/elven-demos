/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Filename CyclicBarrierTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 2019/10/15 23:43</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
            System.out.println(Thread.currentThread().getName()+" barrierAction continue");
        });

        System.out.println(Thread.currentThread().getName()+" start");
        for (int i = 0; i < 5; i++) {
            new MyThread(cyclicBarrier).start();
        }

    }

    static class MyThread extends Thread{
        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));

                System.out.println(Thread.currentThread().getName() + " cyclicBarrier.NumberWaiting:"+cyclicBarrier.getNumberWaiting());

                cyclicBarrier.await();

                System.out.println(Thread.currentThread().getName()+" continue");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}