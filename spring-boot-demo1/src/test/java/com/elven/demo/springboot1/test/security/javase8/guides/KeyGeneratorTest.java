/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author qiusheng.wu
 * @Filename KeyGeneratorTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/28 18:55</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyGeneratorTest {

    @Test
    public void test1() throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

        // 步骤二：初始化
        keyGenerator.init(56);

        // 步骤三：生成
        SecretKey secretKey = keyGenerator.generateKey();

        System.out.println("getAlgorithm:"+secretKey.getAlgorithm());
        System.out.println("getFormat:"+secretKey.getFormat());
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(secretKey.getEncoded()));
    }

    @Test
    public void test2() throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

        // 步骤二：初始化
        keyGenerator.init(56, SecureRandom.getInstance("SHA1PRNG"));

        // 步骤三：生成
        SecretKey secretKey = keyGenerator.generateKey();

        System.out.println("getAlgorithm:"+secretKey.getAlgorithm());
        System.out.println("getFormat:"+secretKey.getFormat());
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(secretKey.getEncoded()));
    }
}