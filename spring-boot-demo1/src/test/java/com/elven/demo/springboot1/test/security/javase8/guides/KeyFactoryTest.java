/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import sun.security.provider.DSAPrivateKey;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;

/**
 * @author qiusheng.wu
 * @Filename KeyFactoryTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/28 17:10</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyFactoryTest {

    @Test
    public void KeySpec2Key() throws NoSuchAlgorithmException, InvalidKeySpecException {

        // 步骤一：创建KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");

//        KeySpec keySpec = new PKCS8EncodedKeySpec("12345678".getBytes());    // java.security.spec.InvalidKeySpecException: java.security.InvalidKeyException: IOException : Detect premature EOF
//        KeySpec keySpec = new SecretKeySpec("12345678".getBytes(), "MD5withRSA");    // java.security.spec.InvalidKeySpecException: Only RSAPrivate(Crt)KeySpec and PKCS8EncodedKeySpec supported for RSA private keys

        // 步骤二.一：转换PrivateKey
        PrivateKey privateKey = keyFactory.generatePrivate(new DSAPrivateKeySpec(new BigInteger("1"), new BigInteger("1"), new BigInteger("1"), new BigInteger("1")));
        System.out.println("privateKey.getAlgorithm:"+privateKey.getAlgorithm());
        System.out.println("privateKey.getFormat:"+privateKey.getFormat());
        System.out.println("privateKey.getEncoded:"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        // 步骤二.二：转换PublicKey
        PublicKey publicKey = keyFactory.generatePublic(new DSAPublicKeySpec(new BigInteger("1"), new BigInteger("1"), new BigInteger("1"), new BigInteger("1")));
        System.out.println("publicKey.getAlgorithm:"+publicKey.getAlgorithm());
        System.out.println("publicKey.getFormat:"+publicKey.getFormat());
        System.out.println("publicKey.getEncoded:"+ Base64Utils.encodeToString(publicKey.getEncoded()));
    }

    @Test
    public void Key2KeySpec() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        // 步骤一：创建KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");

        // 步骤二：准备Key对象
        PrivateKey privateKey = new DSAPrivateKey(new BigInteger("1"), new BigInteger("1"), new BigInteger("1"), new BigInteger("1"));

        // 步骤三：转换为KeySpec
        DSAPrivateKeySpec keySpec = keyFactory.getKeySpec(privateKey, DSAPrivateKeySpec.class);
        System.out.println("getX:"+keySpec.getX());
        System.out.println("getP:"+keySpec.getP());
        System.out.println("getQ:"+keySpec.getQ());
        System.out.println("getG:"+keySpec.getG());
    }
}