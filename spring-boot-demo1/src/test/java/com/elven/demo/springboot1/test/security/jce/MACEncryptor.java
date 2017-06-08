/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.jce;

import org.apache.tomcat.util.buf.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @Filename java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-8 上午12:14</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MACEncryptor {
    /**
     * 初始化密钥
//     *
     * @return byte[] 密钥
     * @throws Exception
     */
    public byte[] initKey(String algorithm) throws NoSuchAlgorithmException {

        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());

        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

        // 产生秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * 转换byte到hex
     * @param bytes
     * @return
     */
    public String bytes2Hex(byte[] bytes){
        // 做十六进制转换
        return HexUtils.toHexString(bytes);
    }

    /**
     * 消息摘要
     * @param algorithm 密钥算法
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return
     * @throws Exception
     */
    public byte[] encode(String algorithm, byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException

    {

        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());

        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, algorithm);

        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 初始化Mac
        mac.init(secretKey);

        // 执行消息摘要
        return mac.doFinal(data);
    }

    @Test
    public void test1() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = "我有一头小毛驴，我从来也不骑。".getBytes();
        System.out.println(bytes2Hex(encode("HmacMD2", data, initKey("HmacMD2"))));
        System.out.println(bytes2Hex(encode("HmacMD4", data, initKey("HmacMD4"))));
        System.out.println(bytes2Hex(encode("HmacSHA224", data, initKey("HmacSHA224"))));
    }
}