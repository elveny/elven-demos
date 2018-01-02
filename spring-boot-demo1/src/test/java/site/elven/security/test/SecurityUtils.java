/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.security.test;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.util.ByteUtils;

import javax.crypto.*;
import java.security.*;

/**
 * @author qiusheng.wu
 * @Filename SecurityUtils.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/29 15:13</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SecurityUtils {

    /**
     * 生成非对称加密算法密钥对
     * @param algorithm 算法
     * @param keysize 密钥长度
     * @return 密钥对
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair genKeyPair(String algorithm, int keysize) throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

        // 步骤二：初始化
        keyPairGenerator.initialize(keysize);

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        return keyPair;
    }

    /**
     * 生成对称加密算法密钥
     * @param algorithm 算法
     * @param keysize 密钥长度
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Key genKey(String algorithm, int keysize) throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

        // 步骤二：初始化
        keyGenerator.init(keysize);

        // 步骤三：生成
        SecretKey secretKey = keyGenerator.generateKey();

        return secretKey;
    }

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
    public static boolean digestCheck(String algorithm, byte[] data, byte[] digest, String salt) throws NoSuchAlgorithmException {
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

    /**
     * 信息加密
     * @param algorithm 算法
     * @param key 加密密钥
     * @param data 被加密的“明文”
     * @param step 分段加密的“步长”，-1表示不需要分段加密
     * @return 密文
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(String algorithm, Key key, byte[] data, int step) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 创建cipher
        Cipher cipher = Cipher.getInstance(algorithm);

        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, key);

        if(step == -1){
            return cipher.doFinal(data);
        }

        return segment(cipher, data, step);

    }

    /**
     * 信息加密
     * @param algorithm 算法
     * @param key 加密密钥
     * @param data 被加密的“明文”
     * @return 密文
     * @see SecurityUtils encrypt(String algorithm, Key key, byte[] data, int step)
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(String algorithm, Key key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(algorithm, key, data, -1);
    }

    /**
     * 信息解密
     * @param algorithm 算法
     * @param key 解密密钥
     * @param data 被解密的“密文”
     * @param step 分段解密的“步长”，-1表示不需要分段解密
     * @return 解密后的信息
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] decrypt(String algorithm, Key key, byte[] data, int step) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        // 创建cipher
        Cipher cipher = Cipher.getInstance(algorithm);

        // 加密
        cipher.init(Cipher.DECRYPT_MODE, key);

        if(step == -1){
            return cipher.doFinal(data);
        }

        return segment(cipher, data, step);
    }

    /**
     * 信息解密
     * @param algorithm 算法
     * @param key 解密密钥
     * @param data 被解密的“密文”
     * @return 解密后的信息
     * @see SecurityUtils decrypt(String algorithm, Key key, byte[] data, int step)
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] decrypt(String algorithm, Key key, byte[] data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        return decrypt(algorithm, key, data, -1);
    }

    /**
     * 灵活多步加密/解密
     * 注意：加密时，step必须小于keySize/8-11，解密时的step必须固定为keySize/8
     * @param cipher Cipher对象
     * @param in 输入的字节码（明文/密文）
     * @param step 步长
     * @return out 加密/解密后的字节码
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] segment(Cipher cipher, byte[] in, int step) throws BadPaddingException, IllegalBlockSizeException {
        byte[] out = new byte[0];

        // 根据步长进行“多步”加密
        int i = 0;
        int length = in.length;
        while (i < length){
            if(length - i < step){
                step = length - i;
            }

            // 第N步加密
            byte[] temp = cipher.doFinal(in, i, step);
            out = ByteUtils.concat(out, temp);
            i += step;
        }

        return out;
    }

    /**
     * 数字签名
     * @param algorithm 算法
     * @param privateKey 加签私钥
     * @param data 被加签的信息
     * @return 签名值
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static byte[] sign(String algorithm, PrivateKey privateKey, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 步骤一：创建Signature对象
        Signature signature = Signature.getInstance(algorithm);

        // 步骤二：用私钥初始化Signature对象
        signature.initSign(privateKey);

        // 步骤三：更新数据
        signature.update(data);

        // 步骤四：数字签名
        byte[] sign = signature.sign();

        return sign;
    }

    /**
     * 签名验证
     * @param algorithm 算法
     * @param publicKey 验签公钥
     * @param data 签名的原文
     * @param sign 签名值
     * @return 验签结果
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verify(String algorithm, PublicKey publicKey, byte[] data, byte[] sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 步骤一：创建Signature对象
        Signature signature = Signature.getInstance(algorithm);

        // 步骤二：用公钥初始化Signature对象
        signature.initVerify(publicKey);

        // 步骤三：更新数据
        signature.update(data);

        // 步骤四：验证签名
        boolean verify = signature.verify(sign);

        return verify;

    }
}