/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot.batch1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qiusheng.wu
 * @Filename SampleBatchApplication.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/22 16:52</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Application.class, args);
        System.exit(SpringApplication.exit(SpringApplication.run(
                BatchConfiguration.class, args)));
    }
}