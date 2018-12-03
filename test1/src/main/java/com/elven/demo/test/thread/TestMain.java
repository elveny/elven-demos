/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test.thread;

/**
 * @author qiusheng.wu
 * @Filename TestMain.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 11:34</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestMain {
    public static void main(String[] args) {

        TestService testService = new TestService();

        for (int i=0; i<100; i++){
            new Thread(new TestThread("TEST-0", testService)).start();
            new Thread(new TestThread("TEST-1", testService)).start();

//            for (int j = 0; j < 2; j++) {
//                new Thread(new TestThread("TEST-"+j, testService)).start();
//            }

        }

    }
}