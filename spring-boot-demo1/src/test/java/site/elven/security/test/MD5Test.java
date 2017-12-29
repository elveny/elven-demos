/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.security.test;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.NoSuchAlgorithmException;

/**
 * @author qiusheng.wu
 * @Filename MD5Test.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/29 14:39</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MD5Test {
    @Test
    public void test1() throws NoSuchAlgorithmException {
        String algorithm = "MD5";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = MessageDigestUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(MessageDigestUtils.byte2Hex(digest));

        boolean verify = MessageDigestUtils.verify(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void test2() throws NoSuchAlgorithmException {
        String algorithm = "MD2";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = MessageDigestUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(MessageDigestUtils.byte2Hex(digest));

        boolean verify = MessageDigestUtils.verify(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void test3() throws NoSuchAlgorithmException {
        String algorithm = "MD3";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = MessageDigestUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(MessageDigestUtils.byte2Hex(digest));

        boolean verify = MessageDigestUtils.verify(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void test4() throws NoSuchAlgorithmException {
        String algorithm = "MD4";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = MessageDigestUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(MessageDigestUtils.byte2Hex(digest));

        boolean verify = MessageDigestUtils.verify(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

}