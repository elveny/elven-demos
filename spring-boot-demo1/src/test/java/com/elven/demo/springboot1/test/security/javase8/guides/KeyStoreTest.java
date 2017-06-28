/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Enumeration;

/**
 * @Filename KeyStoreTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-29 上午12:40</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyStoreTest {

    @Test
    public void aliasesTest() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

        // 步骤一：创建
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        // 步骤二：准备密钥库文件
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);

        // 步骤三：加载密钥库
        keyStore.load(in, "storepass1223334444".toCharArray());

        // 步骤四：获取密钥库中的别名列表
        Enumeration<String> aliases = keyStore.aliases();

        System.out.println("一大波别名即将到来：");
        while (aliases.hasMoreElements()){
            String alias = aliases.nextElement();
            System.out.println(alias+".isKeyEntry:"+keyStore.isKeyEntry(alias));
            System.out.println(alias+".isCertificateEntry:"+keyStore.isCertificateEntry(alias));
        }

    }

    @Test
    public void getKeyTest() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        // 步骤一：创建
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        // 步骤二：准备密钥库文件
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);

        // 步骤三：加载密钥库
        keyStore.load(in, "storepass1223334444".toCharArray());

        // 步骤四：获取私钥
        PrivateKey privateKey = (PrivateKey) keyStore.getKey("test1", "test1keypass1223334444".toCharArray());

        System.out.println("getAlgorithm："+privateKey.getAlgorithm());
        System.out.println("getEncoded："+Base64Utils.encodeToString(privateKey.getEncoded()));
        System.out.println("getFormat："+privateKey.getFormat());
        System.out.println("isDestroyed："+privateKey.isDestroyed());
    }

    @Test
    public void getEntryTest() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, UnrecoverableEntryException {
        // 步骤一：创建
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        // 步骤二：准备密钥库文件
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);

        // 步骤三：加载密钥库
        keyStore.load(in, "storepass1223334444".toCharArray());

        // 步骤四：获取Entry
        KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("test1", new KeyStore.PasswordProtection("test1keypass1223334444".toCharArray()));

        PrivateKey privateKey = entry.getPrivateKey();

        System.out.println("getAlgorithm："+privateKey.getAlgorithm());
        System.out.println("getEncoded："+Base64Utils.encodeToString(privateKey.getEncoded()));
        System.out.println("getFormat："+privateKey.getFormat());
        System.out.println("isDestroyed："+privateKey.isDestroyed());
    }

    @Test
    public void storeTest() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);

        String keystorePath = "classpath:test_store_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        OutputStream out = new FileOutputStream(keystoreFile);

        keyStore.store(out, "teststorepass1223334444".toCharArray());
    }
}