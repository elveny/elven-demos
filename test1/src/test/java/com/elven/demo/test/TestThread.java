/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test;

/**
 * @author qiusheng.wu
 * @Filename TestThread.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 11:21</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestThread implements Runnable {
    private int id;

    public TestThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("start::::"+threadName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("error::::"+e.getMessage());
        }
        finally {
            System.out.println("end::::"+threadName);
        }

    }
}