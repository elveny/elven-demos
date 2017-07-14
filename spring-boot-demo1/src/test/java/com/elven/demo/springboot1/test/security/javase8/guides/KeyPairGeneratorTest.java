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


    @Test
    public void test4() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException {
        // 步骤一：创建
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        // 步骤二：初始化
        keyPairGenerator.initialize(2048);

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
    public void test5() throws NoSuchAlgorithmException, NoSuchProviderException {
        // 步骤一：创建
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        // 步骤二：初始化
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyGen.generateKeyPair();

        // 步骤四：打印
        printf(keyPair);
    }

    @Test
    public void test6() throws NoSuchAlgorithmException, NoSuchProviderException {
        // 步骤一：创建
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        // 步骤二：初始化
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        random.setSeed("12345678".getBytes());
//        random.setSeed(12345678);
        keyGen.initialize(1024, random);

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyGen.generateKeyPair();

        // 步骤四：打印
        printf(keyPair);
    }

    @Test
    public void test7() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        // 步骤一：创建
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        // 步骤二：初始化
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        random.setSeed("12345678".getBytes());
//        random.setSeed(12345678);
        BigInteger p = new BigInteger("1024");
        BigInteger q = new BigInteger("160");
        BigInteger g = new BigInteger("160");
        DSAParameterSpec dsaSpec = new DSAParameterSpec(p, q, g);

        keyGen.initialize(dsaSpec, random);

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyGen.generateKeyPair();

        // 步骤四：打印
        printf(keyPair);
    }

    private void printf(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("privateKey.getAlgorithm:"+privateKey.getAlgorithm());
        System.out.println("privateKey.getFormat:"+privateKey.getFormat());
        System.out.println("privateKey.getEncoded.length:"+ privateKey.getEncoded().length);
        System.out.println("privateKey.getEncoded:"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("publicKey.getAlgorithm:"+publicKey.getAlgorithm());
        System.out.println("publicKey.getFormat:"+publicKey.getFormat());
        System.out.println("publicKey.getEncoded.length:"+ publicKey.getEncoded().length);
        System.out.println("publicKey.getEncoded:"+ Base64Utils.encodeToString(publicKey.getEncoded()));
    }
}