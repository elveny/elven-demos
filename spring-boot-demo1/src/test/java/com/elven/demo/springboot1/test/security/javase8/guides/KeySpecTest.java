/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;

/**
 * @author qiusheng.wu
 * @Filename KeySpecTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/11 8:37</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeySpecTest {

    @Test
    public void generatePrivateTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger x = BigInteger.valueOf(1), p = BigInteger.valueOf(1), q = BigInteger.valueOf(1), g = BigInteger.valueOf(1);
        DSAPrivateKeySpec dsaPrivKeySpec = new DSAPrivateKeySpec(x, p, q, g);

        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PrivateKey privKey = keyFactory.generatePrivate(dsaPrivKeySpec);

        SecurityUtils.printKey(privKey);
    }

    @Test
    public void generatePublicTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger y = BigInteger.valueOf(1), p = BigInteger.valueOf(1), q = BigInteger.valueOf(1), g = BigInteger.valueOf(1);
        DSAPublicKeySpec dsaPubKeySpec = new DSAPublicKeySpec(y, p, q, g);

        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PublicKey pubKey = keyFactory.generatePublic(dsaPubKeySpec);
        SecurityUtils.printKey(pubKey);
    }
}