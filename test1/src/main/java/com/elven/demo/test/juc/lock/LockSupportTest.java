/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.juc.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author elven
 * @Filename LockSupportTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/15 23:27</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class LockSupportTest {
    private static Thread mainThread;

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " wake up others");
            LockSupport.unpark(mainThread);
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 获取主线程
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + " start myThread");
        myThread.start();

        System.out.println(Thread.currentThread().getName() + " block");
        // 主线程阻塞
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName() + " continue");
    }
}