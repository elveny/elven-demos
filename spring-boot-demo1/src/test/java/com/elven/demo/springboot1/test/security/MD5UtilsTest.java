/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.junit.Test;

import java.security.MessageDigest;

/**
 * @author qiusheng.wu
 * @Filename MD5UtilsTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/21 16:58</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MD5UtilsTest {

    @Test
    public void sign1(){
        String plain = "outTradeNo=201712250000000000017019&transType=ORDER_YZFPAYMENT&merID=113430100000010&accNumber=18888888888&fee=1&reqTime=20171225150240&8ur2uXYwsgF16bvX";
        System.out.println(MD5Encode(plain, "UTF-8").toUpperCase());
    }

    @Test
    public void sign2() throws Exception {
        String plain = "outTradeNo=201712250000000000017019&transType=ORDER_YZFPAYMENT&merID=113430100000010&accNumber=18888888888&fee=1&reqTime=20171225150240&";
        System.out.println(com.msxf.paygw.common.util.MD5Util.md5UpperCase(plain, "8ur2uXYwsgF16bvX"));
    }

    @Test
    public void sign3(){
        String plain = "outTradeNo=201712250000000000017051&code=0001&resultCode=200013&accDate=20171225&8ur2uXYwsgF16bvX";
        System.out.println(MD5Encode(plain, "UTF-8").toUpperCase());
    }

    @Test
    public void sign4() throws Exception {
        String plain = "outTradeNo=201712250000000000017051&code=0001&resultCode=200013&accDate=20171225&";
        System.out.println(com.msxf.paygw.common.util.MD5Util.md5UpperCase(plain, "8ur2uXYwsgF16bvX"));
    }

    public static String MD5Encode(String src, String charset) {
        String md5String ="";
        try {
            MessageDigest md5 = null;
            md5 = MessageDigest.getInstance("MD5");

            byte[] byteArray = null;
            byteArray = src.getBytes(charset);

            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer(32);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            md5String = hexValue.toString();
        } catch (Exception e) {
            md5String = "";
            e.printStackTrace();
        }
        return md5String;
    }
}