/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author qiusheng.wu
 * @Filename RSAUtilsTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/12 15:36</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSAUtilsTest {

    @Test
    public void test1() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException {
        RSAUtils rsaUtils = new RSAUtils();

        String message = "我有一头小毛驴我从来也不骑";
        byte[] encryptMessage = rsaUtils.publicEncrypt(message.getBytes());
        byte[] decryptMessage = rsaUtils.privateDecrypt(encryptMessage);

        System.out.println("明文:::::"+message);
        System.out.println("公钥加密后的密文（Base64编码）:::::"+ Base64Utils.encodeToString(encryptMessage));
        System.out.println("私钥解密后的明文:::::"+ new String(decryptMessage));

    }

    @Test
    public void test2() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException {
        RSAUtils rsaUtils = new RSAUtils();

        String message = "我有一头小毛驴我从来也不骑";
        byte[] encryptMessage = rsaUtils.privateEncrypt(message.getBytes());
        byte[] decryptMessage = rsaUtils.publicDecrypt(encryptMessage);

        System.out.println("明文:::::"+message);
        System.out.println("私钥加密后的密文（Base64编码）:::::"+ Base64Utils.encodeToString(encryptMessage));
        System.out.println("公钥解密后的明文:::::"+ new String(decryptMessage));

    }
}