/**
 * elven.tech Inc.
 * Copyright (cipher) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
public class DESUtilsTest {

    @Test
    public void des() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        System.out.println("/////////////////////////////////////////"+DESUtils.ALGORITHM_DES+"///////////////////////////////////////////////////////");
        DESUtils desUtils = new DESUtils(DESUtils.ALGORITHM_DES,"11111111".getBytes());
        String msg ="abcdefg";
        byte[] encontent = desUtils.encryptor(msg);
        byte[] decontent = desUtils.decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + new String(encontent));
        System.out.println("加密后:" + Base64Utils.encodeToString(encontent));
        System.out.println("解密后:" + new String(decontent));
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
    }

    @Test
    public void des3() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        System.out.println("/////////////////////////////////////////"+DESUtils.ALGORITHM_3DES+"///////////////////////////////////////////////////////");
        DESUtils desUtils = new DESUtils(DESUtils.ALGORITHM_3DES,"111111112222222233333333".getBytes());
        String msg ="abcdefg";
        byte[] encontent = desUtils.encryptor(msg);
        byte[] decontent = desUtils.decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + new String(encontent));
        System.out.println("加密后:" + Base64Utils.encodeToString(encontent));
        System.out.println("解密后:" + new String(decontent));
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
    }

    @Test
    public void aes() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        System.out.println("/////////////////////////////////////////"+DESUtils.ALGORITHM_AES+"///////////////////////////////////////////////////////");
        DESUtils desUtils = new DESUtils(DESUtils.ALGORITHM_AES,"1111111122222222".getBytes());
        String msg ="abcdefg";
        byte[] encontent = desUtils.encryptor(msg);
        byte[] decontent = desUtils.decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + new String(encontent));
        System.out.println("加密后:" + Base64Utils.encodeToString(encontent));
        System.out.println("解密后:" + new String(decontent));
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
    }
}