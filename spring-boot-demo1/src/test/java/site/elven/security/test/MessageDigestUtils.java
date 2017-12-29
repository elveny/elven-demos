/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.security.test;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.util.ByteUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author qiusheng.wu
 * @Filename MessageDigestUtils.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/29 15:13</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MessageDigestUtils {

    /**
     * 字节码转为十六进制字符串
     * @param data
     * @return
     */
    public static String byte2Hex(byte[] data){
        StringBuilder result = new StringBuilder("");
        for (byte b : data) {
            if ((b & 0xff) < 0x10) {
                result.append("0");
            }
            result.append(Long.toString(b & 0xff, 16));
        }
        return result.toString();
    }

    /**
     * 计算摘要
     * @param algorithm 摘要算法
     * @param data 明文数据
     * @param salt “盐”：用于增加摘要的强度
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] digest(String algorithm, byte[] data, String salt) throws NoSuchAlgorithmException {
        // 步骤一：加点“盐”（非必须）
        if(StringUtils.isNotBlank(salt)){
            data = ByteUtils.concat(data, salt.getBytes());
        }

        // 步骤二：创建MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

        // 步骤三：update数据
        messageDigest.update(data);

        // 步骤四：计算摘要
        byte[] digest = messageDigest.digest();

        return digest;
    }

    /**
     * 摘要验证
     * @param algorithm 摘要算法
     * @param data 明文数据
     * @param digest 摘要数据
     * @param salt “盐”：用于增加摘要的强度
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean verify(String algorithm, byte[] data, byte[] digest, String salt) throws NoSuchAlgorithmException {
        // 步骤一：加点“盐”（非必须）
        if(StringUtils.isNotBlank(salt)){
            data = ByteUtils.concat(data, salt.getBytes());
        }

        // 步骤二：创建MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

        // 步骤三：update数据
        messageDigest.update(data);

        // 步骤四：比较摘要是否一致
        if(byte2Hex(messageDigest.digest()).equalsIgnoreCase(byte2Hex(digest))){
            return true;
        }

        return false;
    }
}