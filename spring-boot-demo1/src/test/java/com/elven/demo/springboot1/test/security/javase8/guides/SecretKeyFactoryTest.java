/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author qiusheng.wu
 * @Filename SecretKeyFactoryTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/28 18:09</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SecretKeyFactoryTest {

    @Test
    public void generateSecretTest() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 步骤一：创建SecretKeyFactory对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        // 步骤二：准备KeySpec
        byte[] desKeyData = { (byte)0x01, (byte)0x02, (byte)0x03,
                (byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07, (byte)0x08 };
        DESKeySpec desKeySpec = new DESKeySpec(desKeyData);

        // 步骤三：根据KeySpec生成SecretKey
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        System.out.println("getAlgorithm:"+secretKey.getAlgorithm());
        System.out.println("getFormat:"+secretKey.getFormat());
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(secretKey.getEncoded()));
    }

    @Test
    public void getKeySpecTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 步骤一：创建SecretKeyFactory对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        // 步骤二：准备SecretKeySpec
        byte[] desKeyData = { (byte)0x01, (byte)0x02, (byte)0x03,
                (byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07, (byte)0x08 };

        SecretKeySpec secretKey = new SecretKeySpec(desKeyData, "DES");

        // 步骤三：根据SecretKey生成KeySpec
        DESKeySpec desKeySpec = (DESKeySpec) keyFactory.getKeySpec(secretKey, DESKeySpec.class);
        System.out.println("getKey:"+Base64Utils.encodeToString(desKeySpec.getKey()));
    }
}