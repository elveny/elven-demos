/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author qiusheng.wu
 * @Filename BlowfishKeyTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/11 13:28</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class BlowfishKeyTest {

    @Test
    public void test1() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        String cleartext = "我有一只小毛驴我从来也不骑";

        byte[] encrypted =
                cipher.doFinal(cleartext.getBytes());
        System.out.println(Base64Utils.encodeToString(encrypted));

    }

    @Test
    public void test2() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        String cleartext = "我有一只小毛驴我从来也不骑";

        byte[] encrypted =
                cipher.doFinal(cleartext.getBytes());
        System.out.println(Base64Utils.encodeToString(encrypted));

    }

}