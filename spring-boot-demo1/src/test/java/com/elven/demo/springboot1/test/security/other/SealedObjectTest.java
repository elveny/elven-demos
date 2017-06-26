/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.other;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * @author qiusheng.wu
 * @Filename SealedObjectTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/27 1:20</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SealedObjectTest {

    /**
     * 密钥算法 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥
     */
    public static final String KEY_ALGORITHM = "DES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    /**
     * 转换密钥
     *
     * @param key
     *            二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    public Key toKey(String key) throws Exception
    {

        // 实例化DES密钥材料
        DESKeySpec dks = new DESKeySpec(key.getBytes());

        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

        // 生成秘密密钥
        SecretKey secretKey = keyFactory.generateSecret(dks);

        return secretKey;
    }

    @Test
    public void getObjectFromCipherTest() throws Exception {
        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        SealedObject so = new SealedObject(originalText, cipher);

        // 步骤四：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤五：从SealedObject对象中取得明文
        String plainText = (String) so.getObject(cipher);
        System.out.println("明文："+new String(plainText));

    }

    @Test
    public void getObjectFromKeyTest() throws Exception {
        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        SealedObject so = new SealedObject(originalText, cipher);

        // 步骤四：从SealedObject对象中取得明文
        String plainText = (String) so.getObject(toKey("12345678"));
        System.out.println("明文："+new String(plainText));

    }
}