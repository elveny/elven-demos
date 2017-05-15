/**
 * elven.tech Inc.
 * Copyright (cipher) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @Filename DESUtils.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-5-11 下午11:51</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DESUtils {
    // 加密算法
    public final static String ALGORITHM_DES = "DES";
    public final static String ALGORITHM_3DES = "DESede";
    public final static String ALGORITHM_AES = "AES";

    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator generator;
    //SecretKey 负责保存对称密钥
    private SecretKey secretKey;
    //Cipher负责完成加密或解密工作
    private Cipher cipher;
    //该字节数组负责保存加密的结果
    private byte[] cipherByte;

    public DESUtils(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        generator = KeyGenerator.getInstance(algorithm);
        //生成密钥
        secretKey = generator.generateKey();
        //生成Cipher对象,指定其支持的DES算法
        cipher = Cipher.getInstance(algorithm);
    }

    public DESUtils(String algorithm, byte[] keys) throws NoSuchPaddingException, NoSuchAlgorithmException {
        //生成密钥
        secretKey = new SecretKeySpec(keys, algorithm);
        //生成Cipher对象,指定其支持的DES算法
        cipher = Cipher.getInstance(algorithm);
    }

    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] encryptor(String str) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] src = str.getBytes();
        // 加密，结果保存进cipherByte
        cipherByte = cipher.doFinal(src);
        return cipherByte;
    }

    /**
     * 对字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decryptor(byte[] buff) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        cipherByte = cipher.doFinal(buff);
        return cipherByte;
    }

    /**
     * @param args
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws Exception {
        DESUtils desUtils = new DESUtils(ALGORITHM_DES, "11111111".getBytes());
        String msg ="abcdefg";
        byte[] encontent = desUtils.encryptor(msg);
        byte[] decontent = desUtils.decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + new String(encontent));
        System.out.println("加密后:" + Base64Utils.encodeToString(encontent));
        System.out.println("解密后:" + new String(decontent));
    }

}