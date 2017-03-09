/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test.thread;

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

    private String assetPackageNo;
    private TestService testService;

    public TestThread(String assetPackageNo) {
        this.assetPackageNo = assetPackageNo;
    }

    public TestThread(String assetPackageNo, TestService testService) {
        this.assetPackageNo = assetPackageNo;
        this.testService = testService;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("assetPackageNo::::"+assetPackageNo+", threadName::::"+threadName+",start::::");
        try {
            testService.updatePackageCount(assetPackageNo);
        } catch (InterruptedException e) {
            System.out.println("assetPackageNo::::"+assetPackageNo+", threadName::::"+threadName+",error::::"+e.getMessage());
        }
        finally {
            System.out.println("assetPackageNo::::"+assetPackageNo+", threadName::::"+threadName+",::::end, count::::"+testService.getPackageCount(assetPackageNo));
        }

    }
}