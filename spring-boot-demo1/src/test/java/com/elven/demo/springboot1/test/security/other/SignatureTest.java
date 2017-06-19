/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.other;

import com.elven.demo.springboot1.test.security.jca.KeyPairGeneratorUtils;
import org.junit.After;
import org.junit.Before;
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
 * <li>Date: 17-6-19 下午11:52</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SignatureTest {

    private KeyPair keypair = null;

    private String data = "我有一头小毛驴我从来也不骑";
    private byte[] sign = null;

    @Before
    public void genKeyPair() throws NoSuchAlgorithmException {
        keypair = new KeyPairGeneratorUtils().genKeyPair("RSA", 576);
    }

    @Test
    public void sign() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 步骤一：创建Signature对象
        Signature signature = Signature.getInstance("SHA1WithRSA");

        // 步骤二：用私钥初始化Signature对象
        signature.initSign(keypair.getPrivate());

        // 步骤三：更新数据
        signature.update(data.getBytes());

        // 步骤四：数字签名
        sign = signature.sign();
        System.out.println("加签结果："+Base64Utils.encodeToString(sign));

    }

    @After
    public void verify() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 步骤一：创建Signature对象
        Signature signature = Signature.getInstance("SHA1WithRSA");

        // 步骤二：用私钥初始化Signature对象
        signature.initVerify(keypair.getPublic());

        // 步骤三：更新数据
        signature.update(data.getBytes());

        // 步骤四：数字签名
        boolean verify = signature.verify(sign);
        System.out.println("验签结果："+verify);

    }
}