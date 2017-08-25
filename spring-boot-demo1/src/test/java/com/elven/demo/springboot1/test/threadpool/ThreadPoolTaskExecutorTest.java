/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author qiusheng.wu
 * @Filename ThreadPoolTaskExecutorTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/20 22:32</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ThreadPoolTaskExecutorTest {

    static Logger logger = LoggerFactory.getLogger(ThreadPoolTaskExecutorTest.class);

    public static void main(String[] args) throws InterruptedException {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(0);
        threadPoolTaskExecutor.setMaxPoolSize(10);
//        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler(){

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                logger.info("rejectedExecution::::{}", r);
                threadPoolTaskExecutor.execute(r);
            }
        });
        threadPoolTaskExecutor.initialize();

        for(int i =0; i<100; i++){
            threadPoolTaskExecutor.execute(new TestThread(i));
        }

        while (true){
            logger.info(""+threadPoolTaskExecutor.getThreadPoolExecutor());
            logger.info("getCorePoolSize::::"+threadPoolTaskExecutor.getThreadPoolExecutor().getCorePoolSize());

//            int r = RandomUtils.nextInt(10);
//            logger.info("RandomUtils.nextInt(10)::::{}", r);
//            if(r > 0){
//                threadPoolTaskExecutor.setCorePoolSize(r);
//                threadPoolTaskExecutor.setMaxPoolSize(r);
////                threadPoolTaskExecutor.initialize();
//            }

            Thread.sleep(1000);

        }

    }


}