/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename AlipayRSA2Test.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/29 14:00</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AlipayRSA2Test {

    @Test
    public void genKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidParameterSpecException, InvalidAlgorithmParameterException {

        byte[] publicKeyEncoded = Base64Utils.decodeFromString("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAosr9h2vTQnvt7rjpE4f2SyVUkfaJTIUzezikq9BbkMo/FBO6iF6dk0l5wN96DJBFPwW8HU9I4NL2ewByPpXMUb6dFNaihUxAPs+RATF+88mD9/eCaWjTs908L0y6DK/ejQAOlDs1Dh9zLzeAH0HOFxtqKjIAgW1R/gaSddy65AnQd6jPJ1DS/ZLuqkIEDALT2fqoUHsYas32yGIwTbgnmztWPTSWo2ibTTSWps8T625bHoaMQ27o4GNM/BKGt/a8Ox+Qtro3zqq0uGlp+TGRCRksK21Sqfyfda1IwgMU1f53CeHUwwQA/QDfwkYZki8hi38tVmwanVtkl7DAQlQljQIDAQAB");

        // （1）创建KeyFactory
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // （2）用[B方]的二进制公钥创建KeySpec
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyEncoded);
        // （3）转换[B方]公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        System.out.println(Base64Utils.encodeToString(publicKey.getEncoded()));

        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("RSA");

        paramGen.init(2048);

        AlgorithmParameters params = paramGen.generateParameters();

        RSAKeyGenParameterSpec rsaKeyGenParameterSpec = (RSAKeyGenParameterSpec)params.getParameterSpec(RSAKeyGenParameterSpec.class);

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        // 步骤三：用准备好的AlgorithmParameterSpec初始化KeyPairGenerator对象
        keyPairGenerator.initialize(rsaKeyGenParameterSpec);

        // 步骤四：生成KeyPair
        KeyPair kpair = keyPairGenerator.generateKeyPair();

        PrivateKey _privateKey = kpair.getPrivate();
        PublicKey _publicKey = kpair.getPublic();
        System.out.println(Base64Utils.encodeToString(_publicKey.getEncoded()));
        System.out.println(Base64Utils.encodeToString(_privateKey.getEncoded()));


    }

}

