/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.jca;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.*;

/**
 * @Filename KeyPairGeneratorUtils.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-6 下午11:36</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyPairGeneratorUtils {

    public static KeyPair genKeyPair(String algorithm, int keysize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
        keyGen.initialize(keysize);

        KeyPair keypair = keyGen.genKeyPair();

        return keypair;
    }

    @Test
    public void genDSAKeyPairTest() throws NoSuchAlgorithmException {
        KeyPair keyPair = genKeyPair("DSA", 1024);
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("\n::::::::::::::::::::privateKey::::::::::::::::::::\n"+privateKey);
        System.out.println("Algorithm:::::"+privateKey.getAlgorithm());
        System.out.println("Format:::::"+privateKey.getFormat());
        System.out.println("Base64Utils.encodeToString(Encoded):::::"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("\n::::::::::::::::::::publicKey::::::::::::::::::::\n"+publicKey);
        System.out.println("Algorithm:::::"+publicKey.getAlgorithm());
        System.out.println("Format:::::"+publicKey.getFormat());
        System.out.println("Base64Utils.encodeToString(Encoded):::::"+Base64Utils.encodeToString(publicKey.getEncoded()));

    }

    @Test
    public void genDHKeyPairTest() throws NoSuchAlgorithmException {
        KeyPair keyPair = genKeyPair("DH", 576);
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("\n::::::::::::::::::::privateKey::::::::::::::::::::\n"+privateKey);
        System.out.println("Algorithm:::::"+privateKey.getAlgorithm());
        System.out.println("Format:::::"+privateKey.getFormat());
        System.out.println("Base64Utils.encodeToString(Encoded):::::"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("\n::::::::::::::::::::publicKey::::::::::::::::::::\n"+publicKey);
        System.out.println("Algorithm:::::"+publicKey.getAlgorithm());
        System.out.println("Format:::::"+publicKey.getFormat());
        System.out.println("Base64Utils.encodeToString(Encoded):::::"+Base64Utils.encodeToString(publicKey.getEncoded()));

    }

    @Test
    public void genRSAKeyPairTest() throws NoSuchAlgorithmException {
        KeyPair keyPair = genKeyPair("RSA", 576);
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("\n::::::::::::::::::::privateKey::::::::::::::::::::\n"+privateKey);
        System.out.println("Algorithm:::::"+privateKey.getAlgorithm());
        System.out.println("Format:::::"+privateKey.getFormat());
        System.out.println("Base64Utils.encodeToString(Encoded):::::"+ Base64Utils.encodeToString(privateKey.getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("\n::::::::::::::::::::publicKey::::::::::::::::::::\n"+publicKey);
        System.out.println("Algorithm:::::"+publicKey.getAlgorithm());
        System.out.println("Format:::::"+publicKey.getFormat());
        System.out.println("Base64Utils.encodeToString(Encoded):::::"+Base64Utils.encodeToString(publicKey.getEncoded()));

    }
}