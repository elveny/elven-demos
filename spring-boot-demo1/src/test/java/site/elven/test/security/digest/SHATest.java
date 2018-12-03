/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.test.security.digest;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import site.elven.test.security.common.utils.SecurityUtils;

import java.security.NoSuchAlgorithmException;

/**
 * @author qiusheng.wu
 * @Filename SHATest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/29 15:07</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SHATest {

    @Test
    public void digestTest1() throws NoSuchAlgorithmException {
        String algorithm = "SHA";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = SecurityUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(SecurityUtils.byte2Hex(digest));

        boolean verify = SecurityUtils.digestCheck(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void digestTest2() throws NoSuchAlgorithmException {
        String algorithm = "SHA-1";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = SecurityUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(SecurityUtils.byte2Hex(digest));

        boolean verify = SecurityUtils.digestCheck(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void digestTest3() throws NoSuchAlgorithmException {
        String algorithm = "SHA-224";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = SecurityUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(SecurityUtils.byte2Hex(digest));

        boolean verify = SecurityUtils.digestCheck(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void digestTest4() throws NoSuchAlgorithmException {
        String algorithm = "SHA-256";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = SecurityUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(SecurityUtils.byte2Hex(digest));

        boolean verify = SecurityUtils.digestCheck(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void digestTest5() throws NoSuchAlgorithmException {
        String algorithm = "SHA-384";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = SecurityUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(SecurityUtils.byte2Hex(digest));

        boolean verify = SecurityUtils.digestCheck(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }

    @Test
    public void digestTest6() throws NoSuchAlgorithmException {
        String algorithm = "SHA-512";
        String plain = "我有一头小毛驴我从来也不骑";
        String salt = "1223334444";

        byte[] digest = SecurityUtils.digest(algorithm, plain.getBytes(), salt);
        System.out.println(digest.length);
        System.out.println(Base64Utils.encodeToString(digest));
        System.out.println(SecurityUtils.byte2Hex(digest));

        boolean verify = SecurityUtils.digestCheck(algorithm, plain.getBytes(), digest, salt);
        System.out.println(verify);
    }
}