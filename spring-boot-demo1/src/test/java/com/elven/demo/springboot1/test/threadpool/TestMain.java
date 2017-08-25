/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.threadpool;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author qiusheng.wu
 * @Filename TestMain.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/23 20:01</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {
//        TestThread testThread = new TestThread(1, true);
//        Thread thread = new Thread(testThread);
//        thread.setDaemon(true);
//        thread.start();
//
//        Thread.sleep(1000*10);

        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是TimerTask，我还在...");
            }
        }, 10, 1000);

        Thread.sleep(1000*10);

    }
}