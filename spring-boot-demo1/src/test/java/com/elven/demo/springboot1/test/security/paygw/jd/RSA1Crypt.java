/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

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

    /**
     * RSA最大加密明文大小
     */
    protected static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    protected static final int MAX_DECRYPT_BLOCK = 128;

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
}