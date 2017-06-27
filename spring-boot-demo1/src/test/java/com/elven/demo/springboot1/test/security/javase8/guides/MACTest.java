/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @Filename MACTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-28 上午12:28</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MACTest {

    /**
     * 测试生成SecretKey的方法：new javax.crypto.spec.SecretKeySpec
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    @Test
    public void SecretKeySpecTest() throws NoSuchAlgorithmException, InvalidKeyException {
        // 步骤一：创建Mac对象
        Mac mac = Mac.getInstance("HmacMD5");

        // 步骤二：初始化mac对象
        SecretKeySpec keySpec = new SecretKeySpec("12345678".getBytes(), "HmacMD5");
        mac.init(keySpec);

        // 步骤三：计算MAC
        byte[] MAC = mac.doFinal("我有一头小毛驴我从来也不骑".getBytes());
        System.out.println(Base64Utils.encodeToString(MAC));
    }

    /**
     * 测试生成SecretKey的方法：javax.crypto.KeyGenerator.generateKey()
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    @Test
    public void KeyGeneratorGenerateKeyTest() throws NoSuchAlgorithmException, InvalidKeyException {
        // 步骤一：创建Mac对象
        Mac mac = Mac.getInstance("HmacMD5");

        // 步骤二：初始化mac对象
        Key key = KeyGenerator.getInstance("HmacMD5").generateKey();
        mac.init(key);

        // 步骤三：计算MAC
        byte[] MAC = mac.doFinal("我有一头小毛驴我从来也不骑".getBytes());
        System.out.println(Base64Utils.encodeToString(MAC));
    }

    /**
     * 测试生成SecretKey的方法：javax.crypto.KeyAgreement.generateSecret()
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    @Test
    public void KeyAgreementGenerateSecretTest() throws NoSuchAlgorithmException, InvalidKeyException {
        // 步骤一：创建Mac对象
        Mac mac = Mac.getInstance("HmacMD5");

        // 步骤二：初始化mac对象
        Key key = KeyAgreement.getInstance("HmacMD5").generateSecret("12345678");
        mac.init(key);

        // 步骤三：计算MAC
        byte[] MAC = mac.doFinal("我有一头小毛驴我从来也不骑".getBytes());
        System.out.println(Base64Utils.encodeToString(MAC));
    }

    /**
     * 测试Mac对象算法和SecretKey算法不一致的情况
     * （1）创建Mac对象使用的算法是HmacMD5
     * （2）SecretKey的算法是HmacSHA1
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    @Test
    public void differentAlgorithmOfMacObjectAndSecretKeyTest() throws NoSuchAlgorithmException, InvalidKeyException {
        // 步骤一：创建Mac对象
        Mac mac = Mac.getInstance("HmacMD5");

        // 步骤二：初始化mac对象
        SecretKeySpec keySpec = new SecretKeySpec("12345678".getBytes(), "HmacSHA1");
        mac.init(keySpec);

        // 步骤三：计算MAC
        byte[] MAC = mac.doFinal("我有一头小毛驴我从来也不骑".getBytes());
        System.out.println(Base64Utils.encodeToString(MAC));
    }
}