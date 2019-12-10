/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Filename ReentrantLockTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 2019/10/15 19:38</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        int num = 10;
        ReentrantLock lock = new ReentrantLock(true);

        MyTicket ticket = new MyTicket(lock, num);

        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();

//        for (int i=0; i<10; i++){
//            new Thread(ticket).start();
//        }

    }

    static class MyTicket implements Runnable{

        private ReentrantLock lock;

        private int num;

        public MyTicket(ReentrantLock lock, int num) {
            this.lock = lock;
            this.num = num;
        }

        @Override
        public void run() {

            while (num > 0){
                lock.lock();
                try{
                    if(num > 0){

                        num--;
                        System.out.println(Thread.currentThread().getName() + ":num:"+num);

                    }
                    else {
                        System.out.println("there are no more tickets");
                    }

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                } finally {
                    lock.unlock();
                }
            }


        }
    }
}

