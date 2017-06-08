/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.jca;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.*;

/**
 * @Filename SignatureTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-6 下午11:58</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SignatureTest {

    @Test
    public void signTest() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyPair keyPair = new KeyPairGeneratorUtils().genKeyPair("DSA", 1024);
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] testdata = { 1, 2, 3, 4, 5 };
        Signature signature = Signature.getInstance(privateKey.getAlgorithm());
        signature.initSign(privateKey);
        signature.update(testdata);
        byte[] signedData = signature.sign();
        System.out.println(Base64Utils.encodeToString(signedData));
    }

    @Test
    public void verifySignTest() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 生成密钥对
        KeyPair keyPair = new KeyPairGeneratorUtils().genKeyPair("DSA", 1024);

        // 私钥加签
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] testData = { 1, 2, 3, 4, 5 };
        Signature signSignature = Signature.getInstance(privateKey.getAlgorithm());
        signSignature.initSign(privateKey);
        signSignature.update(testData);
        byte[] signedData = signSignature.sign();
        System.out.println(Base64Utils.encodeToString(signedData));

        // 公钥验签
        PublicKey publicKey = keyPair.getPublic();
        Signature verifySignature = Signature.getInstance(publicKey.getAlgorithm());
        verifySignature.initVerify(publicKey);
        verifySignature.update(testData);
        boolean verifyResult = verifySignature.verify(signedData);
        System.out.println(verifyResult);
    }
}