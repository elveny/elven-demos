/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;

/**
 * @author qiusheng.wu
 * @Filename RSATest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/14 16:59</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSATest {

    @Test
    public void test1() throws IOException {

        byte[] baofoo_deduct_private_key = IOUtils.toByteArray(new FileInputStream(ResourceUtils.getFile("classpath:baofoo/baofoo_dev_baofoo_deduct_private_key.pfx")));
        byte[] baofoo_deduct_private_key_parse = KeyUtils.parse(CertificateType.PKCS12, baofoo_deduct_private_key, "123456", "api_alias", "123456");
        RSA1Crypt crypt = new RSA1Crypt();
        crypt.setKeyBytes(baofoo_deduct_private_key_parse);

        byte[] plian = "我有一只小毛驴我从来也不骑".getBytes();
        byte[] sign = crypt.sign(plian);
        System.out.println(Base64Utils.encodeToString(sign));
    }

    @Test
    public void test2() throws IOException {

        byte[] baofoo_deduct_private_key = IOUtils.toByteArray(new FileInputStream(ResourceUtils.getFile("classpath:baofoo/baofoo_dev_baofoo_deduct_private_key.pfx")));
        byte[] baofoo_deduct_private_key_parse = KeyUtils.parse(CertificateType.PKCS12, baofoo_deduct_private_key, "123456", "api_alias", "123456");
        byte[] plian = "我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑我有一只小毛驴我从来也不骑".getBytes();

        RSA1Crypt crypt = new RSA1Crypt();
        crypt.setKeyBytes(baofoo_deduct_private_key_parse);
        byte[] sign = crypt.encrypt(plian, (PrivateKey) crypt.getDecryptKey(crypt.getKeyBytes()));
        System.out.println(Base64Utils.encodeToString(sign));

        RSA2Crypt crypt2 = new RSA2Crypt();
        crypt2.setKeyBytes(baofoo_deduct_private_key_parse);
        byte[] sign2 = crypt2.encrypt(plian, (PrivateKey) crypt.getDecryptKey(crypt2.getKeyBytes()));
        System.out.println(Base64Utils.encodeToString(sign2));

        RSA3Crypt crypt3 = new RSA3Crypt();
        crypt3.setKeyBytes(baofoo_deduct_private_key_parse);
        byte[] sign3 = crypt3.encrypt(plian, (PrivateKey) crypt.getDecryptKey(crypt3.getKeyBytes()));
        System.out.println(Base64Utils.encodeToString(sign3));
    }
}