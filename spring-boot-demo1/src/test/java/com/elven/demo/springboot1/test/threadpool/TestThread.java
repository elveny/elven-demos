/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.threadpool;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiusheng.wu
 * @Filename TestThread.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/23 19:58</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestThread implements Runnable{
    static Logger logger = LoggerFactory.getLogger(TestThread.class);

    private int id;

    private boolean loop;

    public TestThread(int id) {

        logger.info("开始创建####id##{}", id);
        this.id = id;
    }

    public TestThread(int id, boolean loop) {
        this.id = id;
        this.loop = loop;
    }

    @Override
    public String toString() {
        return "#TestThread#"+id;
    }

    @Override
    public void run() {
        logger.info("开始运行::::id::{}", id);

        if(loop){
            while (true){
                try {
                    Thread.sleep(1000);
                    logger.info("我在循环...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        try {
            int r = RandomUtils.nextInt(10);
            Thread.sleep(1000*10*r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("结束运行====id=={}", id);
    }
}