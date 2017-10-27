/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.jca;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Filename MessageDigestTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-5-11 下午10:43</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MessageDigestTest {

    private byte[] messageDigest(String algorithm, String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] digest = messageDigest.digest(message.getBytes());

            return digest;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Test
    public void md2Test() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("MD2", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void md5Test() {
        String message = "1330http://blog.csdn.net/jadyer1330";
        byte[] digest = messageDigest("MD5", message);
        System.out.println(new BigInteger(1, digest).toString(16).toUpperCase());
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void SHATest() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("SHA", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void SHA1Test() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("SHA-1", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void SHA256Test() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("SHA-256", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void SHA224Test() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("SHA-224", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void SHA384Test() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("SHA-384", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }

    @Test
    public void SHA512Test() {
        String message = "我有一头小毛驴我从来也不骑";
        byte[] digest = messageDigest("SHA-512", message);
        System.out.println(new BigInteger(1, digest).toString(16));
        System.out.println(Base64Utils.encodeToString(digest));
    }
}