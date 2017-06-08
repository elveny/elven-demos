/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.jce;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Filename DESEncryptor.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-7 下午11:11</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DESEncryptor {
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
    private Key toKey(byte[] key) throws Exception
    {

        // 实例化DES密钥材料
        DESKeySpec dks = new DESKeySpec(key);

        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

        // 生成秘密密钥
        SecretKey secretKey = keyFactory.generateSecret(dks);

        return secretKey;
    }

    /**
     * 加密
     *
     * @param data
     *            待加密数据
     * @param key
     *            密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public byte[] encrypt(byte[] data, byte[] key) throws Exception
    {

        // 还原密钥
        Key k = toKey(key);

        // 实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);

        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data
     *            待解密数据
     * @param key
     *            密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public byte[] decrypt(byte[] data, byte[] key) throws Exception
    {

        // 还原密钥
        Key k = toKey(key);

        // 实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);

        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 生成密钥 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥 <br>
     *
     * @return byte[] 二进制密钥
     * @throws Exception
     */
    public byte[] initKey() throws Exception
    {

        /*
         * 实例化密钥生成器
         *
         * 若要使用64bit密钥注意替换 将下述代码中的KeyGenerator.getInstance(CIPHER_ALGORITHM);
         * 替换为KeyGenerator.getInstance(CIPHER_ALGORITHM, "BC");
         */
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        /*
         * 初始化密钥生成器 若要使用64bit密钥注意替换 将下述代码kg.init(56); 替换为kg.init(64);
         */
        kg.init(56, new SecureRandom());

        // 生成秘密密钥
        SecretKey secretKey = kg.generateKey();

        // 获得密钥的二进制编码形式
        return secretKey.getEncoded();
    }

    @Test
    public void test1() throws Exception {
        String key = "12345678";
        String src = "我有一头小毛驴，我从来也不骑。";
        System.out.println("原文::::"+src);
        byte[] encrypt = encrypt(src.getBytes(), key.getBytes());
        System.out.println("密文::::"+Base64Utils.encodeToString(encrypt));
        byte[] decrypt = decrypt(encrypt, key.getBytes());
        System.out.println("解密::::"+new String(decrypt));

    }

    @Test
    public void test2() throws Exception {
        byte[] key = initKey();
        String src = "我有一头小毛驴，我从来也不骑。";
        System.out.println("原文::::"+src);
        byte[] encrypt = encrypt(src.getBytes(), key);
        System.out.println("密文::::"+Base64Utils.encodeToString(encrypt));
        byte[] decrypt = decrypt(encrypt, key);
        System.out.println("解密::::"+new String(decrypt));
    }

}