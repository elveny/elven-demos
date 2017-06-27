/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Filename MessageDigestTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-19 下午11:11</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MessageDigestTest {

    @Test
    public void digest() throws NoSuchAlgorithmException {
        // 步骤一：创建MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        // 步骤二：update数据
        messageDigest.update("我有一头小毛驴我从来也不骑".getBytes());

        // 步骤三：计算摘要
        byte[] digest = messageDigest.digest();
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void digest1() throws NoSuchAlgorithmException, DigestException {
        // 步骤一：创建MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        // 步骤二：计算摘要
        int length = messageDigest.digest("我有一头小毛驴我从来也不骑".getBytes(), 0, 18);
        System.out.println(length);

    }

}