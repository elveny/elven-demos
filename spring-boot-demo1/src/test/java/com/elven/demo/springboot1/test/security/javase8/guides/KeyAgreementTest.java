/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Filename KeyAgreementTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-28 下午10:16</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyAgreementTest {

    public KeyPair genKeyPair(PublicKey publicKey) throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException {

        DHParameterSpec dhParamSpec = null;
        if(ObjectUtils.isEmpty(publicKey)){

            // 步骤一：准备AlgorithmParameterSpec
            AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");

            paramGen.init(512);

            AlgorithmParameters params = paramGen.generateParameters();

            dhParamSpec = (DHParameterSpec)params.getParameterSpec(DHParameterSpec.class);
        }
        else {
            dhParamSpec = ((DHPublicKey)publicKey).getParams();
        }

        // 步骤二：创建KeyPairGenerator
        KeyPairGenerator kpairGen = KeyPairGenerator.getInstance("DH");

        // 步骤三：用准备好的AlgorithmParameterSpec初始化KeyPairGenerator对象
        kpairGen.initialize(dhParamSpec);

        // 步骤四：生成KeyPair
        KeyPair kpair = kpairGen.generateKeyPair();

        return kpair;
    }

    /**
     * 两方密钥协商
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void twoPartiesTest() throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException {

        ///////////////////////////////[A方]步骤一：生成KeyPair，创建并初始化KeyAgreement////////////////////////////////////////////////////
        // （1）生成KeyPair
        KeyPair keyPair_A = genKeyPair(null);

        // （2）创建KeyAgreement
        KeyAgreement keyAgreement_A = KeyAgreement.getInstance("DH");

        // （3）用A的私钥初始化KeyAgreement
        keyAgreement_A.init(keyPair_A.getPrivate());

        // （4）生成A的公钥备用
        byte[] publicEncoded_A = keyPair_A.getPublic().getEncoded();

        ///////////////////////////////[B方]步骤一：转换A的公钥////////////////////////////////////////////////////
        // （1）创建KeyFactory
        KeyFactory keyFactory_B = KeyFactory.getInstance("DH");
        // （2）用[B方]的二进制公钥创建KeySpec
        X509EncodedKeySpec x509KeySpec_A = new X509EncodedKeySpec(publicEncoded_A);
        // （3）转换[B方]公钥
        PublicKey publicKey_A = keyFactory_B.generatePublic(x509KeySpec_A);


        ///////////////////////////////[B方]步骤二：生成KeyPair，创建并初始化KeyAgreement////////////////////////////////////////////////////
        // （1）生成KeyPair
        KeyPair keyPair_B = genKeyPair(publicKey_A);

        // （2）创建KeyAgreement
        KeyAgreement keyAgreement_B = KeyAgreement.getInstance("DH");

        // （3）用A的私钥初始化KeyAgreement
        keyAgreement_B.init(keyPair_B.getPrivate());

        // （4）生成A的公钥备用
        byte[] publicEncoded_B = keyPair_B.getPublic().getEncoded();


        ///////////////////////////////[A方]步骤二：转换B的公钥，并doPhase////////////////////////////////////////////////////
        // （1）创建KeyFactory
        KeyFactory keyFactory_A = KeyFactory.getInstance("DH");
        // （2）用[B方]的二进制公钥创建KeySpec
        X509EncodedKeySpec x509KeySpec_B = new X509EncodedKeySpec(publicEncoded_B);
        // （3）转换[B方]公钥
        PublicKey publicKey_B = keyFactory_A.generatePublic(x509KeySpec_B);


        ///////////////////////////////[A方]步骤三：doPhase////////////////////////////////////////////////////
        keyAgreement_A.doPhase(publicKey_B, true);

        ///////////////////////////////[B方]步骤三：doPhase////////////////////////////////////////////////////
        keyAgreement_B.doPhase(publicKey_A, true);

        ///////////////////////////////[A方]步骤四：生成密钥////////////////////////////////////////////////////
        byte[] sharedSecret_A = keyAgreement_A.generateSecret();

        ///////////////////////////////[B方]步骤四：生成密钥////////////////////////////////////////////////////
        byte[] sharedSecret_B = keyAgreement_B.generateSecret();

        System.out.println("比较两方生成的密钥是否一致：");
        System.out.println(Base64Utils.encodeToString(sharedSecret_A));
        System.out.println(Base64Utils.encodeToString(sharedSecret_B));

    }

    /**
     * 三方密钥协商
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void threePartiesTest() throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException {

        ///////////////////////////////[A方]步骤一：生成KeyPair，创建并初始化KeyAgreement////////////////////////////////////////////////////
        // （1）生成KeyPair
        KeyPair keyPair_A = genKeyPair(null);

        // （2）创建KeyAgreement
        KeyAgreement keyAgreement_A = KeyAgreement.getInstance("DH");

        // （3）用A的私钥初始化KeyAgreement
        keyAgreement_A.init(keyPair_A.getPrivate());

        ///////////////////////////////[B方]步骤一：生成KeyPair，创建并初始化KeyAgreement////////////////////////////////////////////////////
        // （1）生成KeyPair
        KeyPair keyPair_B = genKeyPair(keyPair_A.getPublic());

        // （2）创建KeyAgreement
        KeyAgreement keyAgreement_B = KeyAgreement.getInstance("DH");

        // （3）用A的私钥初始化KeyAgreement
        keyAgreement_B.init(keyPair_B.getPrivate());

        ///////////////////////////////[C方]步骤一：生成KeyPair，创建并初始化KeyAgreement////////////////////////////////////////////////////
        // （1）生成KeyPair
        KeyPair keyPair_C = genKeyPair(keyPair_B.getPublic());

        // （2）创建KeyAgreement
        KeyAgreement keyAgreement_C = KeyAgreement.getInstance("DH");

        // （3）用A的私钥初始化KeyAgreement
        keyAgreement_C.init(keyPair_C.getPrivate());


        ///////////////////////////////[A方]步骤二：doPhase////////////////////////////////////////////////////
        Key ab = keyAgreement_A.doPhase(keyPair_B.getPublic(), false);

        ///////////////////////////////[B方]步骤二：doPhase////////////////////////////////////////////////////
        Key bc = keyAgreement_B.doPhase(keyPair_C.getPublic(), false);

        ///////////////////////////////[C方]步骤二：doPhase////////////////////////////////////////////////////
        Key ca = keyAgreement_C.doPhase(keyPair_A.getPublic(), false);


        ///////////////////////////////[A方]步骤三：doPhase////////////////////////////////////////////////////
        keyAgreement_A.doPhase(bc, true);

        ///////////////////////////////[B方]步骤三：doPhase////////////////////////////////////////////////////
        keyAgreement_B.doPhase(ca, true);

        ///////////////////////////////[C方]步骤三：doPhase////////////////////////////////////////////////////
        keyAgreement_C.doPhase(ab, true);

        ///////////////////////////////[A方]步骤四：生成密钥////////////////////////////////////////////////////
        byte[] sharedSecret_A = keyAgreement_A.generateSecret();

        ///////////////////////////////[B方]步骤四：生成密钥////////////////////////////////////////////////////
        byte[] sharedSecret_B = keyAgreement_B.generateSecret();

        ///////////////////////////////[B方]步骤四：生成密钥////////////////////////////////////////////////////
        byte[] sharedSecret_C = keyAgreement_C.generateSecret();

        System.out.println("比较三方生成的密钥是否一致：");
        System.out.println(Base64Utils.encodeToString(sharedSecret_A));
        System.out.println(Base64Utils.encodeToString(sharedSecret_B));
        System.out.println(Base64Utils.encodeToString(sharedSecret_C));

    }
}