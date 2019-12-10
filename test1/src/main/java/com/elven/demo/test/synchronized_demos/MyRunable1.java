/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.synchronized_demos;

/**
 * @author elven
 * @Filename MyRunable1.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/10 22:18</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MyRunable1 implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}