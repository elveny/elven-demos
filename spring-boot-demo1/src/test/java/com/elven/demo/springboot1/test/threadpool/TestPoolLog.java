/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author qiusheng.wu
 * @Filename TestPoolLog.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/9/5 10:50</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestPoolLog {

    protected static ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    protected static Logger logger = LoggerFactory.getLogger(TestPoolLog.class);

    private static final int queueCapacity = 150;
    static {
        threadPoolTaskExecutor.setCorePoolSize(150);
        threadPoolTaskExecutor.setMaxPoolSize(200);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.initialize();
    }

    public static void main(String[] args) {
        while (true){
            threadPoolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10*1000);
                        logger.info(Thread.currentThread().getName()+"::::"+"我是线程池中的匿名内部线程，我正常");
                    } catch (Exception e) {
                        logger.error("我是线程池中的匿名内部线程，我异常");
                    }
                }
            });
        }

    }
}