/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename EncodedKeySpecTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/28 14:56</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class EncodedKeySpecTest {

    @Test
    public void PKCS8EncodedKeySpecTest(){
        EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec("12345678".getBytes());
        System.out.println("encodedKey:"+Base64Utils.encodeToString("12345678".getBytes()));
        System.out.println("getFormat:"+encodedKeySpec.getFormat());
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(encodedKeySpec.getEncoded()));

    }

    @Test
    public void X509EncodedKeySpecTest(){
        EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec("12345678".getBytes());
        System.out.println("encodedKey:"+Base64Utils.encodeToString("12345678".getBytes()));
        System.out.println("getFormat:"+encodedKeySpec.getFormat());
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(encodedKeySpec.getEncoded()));

    }
}