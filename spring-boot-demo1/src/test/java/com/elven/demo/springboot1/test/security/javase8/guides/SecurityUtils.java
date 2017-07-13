/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.security.Key;

/**
 * @author qiusheng.wu
 * @Filename SecurityUtils.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/11 8:41</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SecurityUtils {
    private final static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static void printKey(Key key){
        logger.info("getAlgorithm: {}", key.getAlgorithm());
        logger.info("getFormat: {}", key.getFormat());
        logger.info("getEncoded: {}",  Base64Utils.encodeToString(key.getEncoded()));
    }

    public static void printKey(Logger logger, Key key){
        logger.info("getAlgorithm: {}", key.getAlgorithm());
        logger.info("getFormat: {}", key.getFormat());
        logger.info("getEncoded: {}",  Base64Utils.encodeToString(key.getEncoded()));
    }
}