/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.DSAParameterSpec;

/**
 * @author qiusheng.wu
 * @Filename KeyPairGeneratorTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/28 18:35</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyPairGeneratorTest {

    @Test
    public void test1() throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

        // 步骤二：初始化
        keyPairGenerator.initialize(512);

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("privateKey.getAlgorithm:"+privateKey.getAlgorithm());
        System.out.println("privateKey.getFormat:"+privateKey.getFormat());
        System.out.println("privateKey.getEncoded:"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("publicKey.getAlgorithm:"+publicKey.getAlgorithm());
        System.out.println("publicKey.getFormat:"+publicKey.getFormat());
        System.out.println("publicKey.getEncoded:"+ Base64Utils.encodeToString(publicKey.getEncoded()));
    }

    @Test
    public void test2() throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

        // 步骤二：初始化
        keyPairGenerator.initialize(512, new SecureRandom("12345678".getBytes()));

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("privateKey.getAlgorithm:"+privateKey.getAlgorithm());
        System.out.println("privateKey.getFormat:"+privateKey.getFormat());
        System.out.println("privateKey.getEncoded:"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("publicKey.getAlgorithm:"+publicKey.getAlgorithm());
        System.out.println("publicKey.getFormat:"+publicKey.getFormat());
        System.out.println("publicKey.getEncoded:"+ Base64Utils.encodeToString(publicKey.getEncoded()));
    }

    @Test
    public void test3() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        // 步骤一：创建
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

        // 步骤二：初始化
        keyPairGenerator.initialize(new DSAParameterSpec(new BigInteger("2"), new BigInteger("2"), new BigInteger("2")), new SecureRandom("12345678".getBytes()));

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("privateKey.getAlgorithm:"+privateKey.getAlgorithm());
        System.out.println("privateKey.getFormat:"+privateKey.getFormat());
        System.out.println("privateKey.getEncoded:"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("publicKey.getAlgorithm:"+publicKey.getAlgorithm());
        System.out.println("publicKey.getFormat:"+publicKey.getFormat());
        System.out.println("publicKey.getEncoded:"+ Base64Utils.encodeToString(publicKey.getEncoded()));
    }
}