/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename RSA1Crypt.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/5 13:20</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSA1Crypt {

    private  byte [] keyBytes;

    /**
     * RSA|SHA1WithRSA|MD5WithRSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 算法名称
     */
    private String signAlgorithm = "SHA1WithRSA";

    /**
     * RSA最大加密明文大小
     */
    protected static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    protected static final int MAX_DECRYPT_BLOCK = 128;
    public String getAlgorithm() {
        return KEY_ALGORITHM;
    }

    public Key getEncryptKey(byte [] keyBytes) {
        return toPublicKey(keyBytes);
    }

    public Key getDecryptKey(byte [] keyBytes) {
        return toPrivateKey(keyBytes);
    }


    /**
     * 提取公钥
     * */
    protected PublicKey toPublicKey(byte [] key){
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        // KEY_ALGORITHM 指定的加密算法
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 提取私钥
     * **/
    protected PrivateKey toPrivateKey(byte [] key){
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            return priKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] sign(byte[] data) {
        try {
            Signature signature = Signature.getInstance(getSignAlgorithm());
            signature.initSign(toPrivateKey(getKeyBytes()));
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verify(byte[] data, byte[] sign) {
        Signature signature = null;
        try {
            signature = Signature.getInstance(getSignAlgorithm());
            signature.initVerify(toPublicKey(getKeyBytes()));
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected String getSignAlgorithm(){
        return signAlgorithm;
    }


    /**
     * 公钥解密
     * @param encryptedData 已加密数据
     * @return
     */
    public byte[] decrypt(byte[] encryptedData, PublicKey publicKey){

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return doFinal(cipher , encryptedData , MAX_DECRYPT_BLOCK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * <p>
     * 私钥加密
     * </p>
     * @param data 源数据

     */
    public byte[] encrypt(byte[] data, PrivateKey privateKey) {
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return doFinal(cipher , data , MAX_ENCRYPT_BLOCK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    protected byte [] doFinal(Cipher cipher ,byte[] data  , int maxBlock) throws Exception{
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > maxBlock) {
                cache = cipher.doFinal(data, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxBlock;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    protected void setKeyBytes(byte[] keyBytes){
        this.keyBytes = keyBytes;
    }

    protected byte [] getKeyBytes(){
        return  this.keyBytes;
    }
}