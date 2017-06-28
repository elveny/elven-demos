/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;

/**
 * @author qiusheng.wu
 * @Filename KeyInterfaceTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/28 11:15</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyInterfaceTest {

    @Test
    public void threeBaseMethodTest(){
        SecretKeySpec keySpec = new SecretKeySpec("12345678".getBytes(), "MD5withRSA");

        System.out.println("getAlgorithm:"+keySpec.getAlgorithm());
        System.out.println("getFormat:"+keySpec.getFormat());
        System.out.println("getEncoded:"+keySpec.getEncoded());
    }

}