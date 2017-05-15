/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.springframework.util.CollectionUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename RSAUtils.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/12 13:28</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSAUtils {

    // 加密算法
    public static final String KEY_ALGORITHM = "RSA";
    /** 貌似默认是RSA/NONE/PKCS1Padding，未验证 */
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /** RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024 */
    public static final int KEY_SIZE = 2048;

    // 公钥
    private final static String PUBLIC_KEY = "PUBLIC_KEY";
    // 私钥
    private final static String PRIVATE_KEY = "PRIVATE_KEY";

    // 密钥对
    private Map<String, byte[]> keyPairMap = new HashMap<String, byte[]>();

    /**
     * @Description 获取密钥对
     * @Params
     * @Return
     * @Exceptions
     */
    public Map<String, byte[]> genKeys() throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyPairGenerator.initialize(KEY_SIZE);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        keyPairMap.put(PUBLIC_KEY, publicKey.getEncoded());
        keyPairMap.put(PRIVATE_KEY, privateKey.getEncoded());

        return keyPairMap;
    }

    /**
     * @Description 获取公钥
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] getPublicKey() throws NoSuchAlgorithmException {
        if(CollectionUtils.isEmpty(keyPairMap)){
            genKeys();
        }

        return keyPairMap.get(PUBLIC_KEY);
    }

    /**
     * @Description 获取私钥
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] getPrivateKey() throws NoSuchAlgorithmException {
        if(CollectionUtils.isEmpty(keyPairMap)){
            genKeys();
        }

        return keyPairMap.get(PRIVATE_KEY);
    }

    /**
     * @Description 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
     * @Params
     * @Return
     * @Exceptions
     */
    public PublicKey restorePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(getPublicKey());

        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);

        PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);

        return publicKey;
    }

    /**
     * @Description 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
     * @Params
     * @Return
     * @Exceptions
     */
    public PrivateKey restorePrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(getPrivateKey());

        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);

        PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);

        return privateKey;
    }

    /**
     * @Description 公钥加密
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] publicEncrypt(byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, restorePublicKey());

        return cipher.doFinal(plainText);
    }

    /**
     * @Description 私钥解密
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] privateDecrypt(byte[] cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, restorePrivateKey());

        return cipher.doFinal(cipherText);
    }

    /**
     * @Description 私钥加密
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] privateEncrypt(byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, restorePrivateKey());

        return cipher.doFinal(plainText);
    }

    /**
     * @Description 公钥解密
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] publicDecrypt(byte[] cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, restorePublicKey());

        return cipher.doFinal(cipherText);
    }
}