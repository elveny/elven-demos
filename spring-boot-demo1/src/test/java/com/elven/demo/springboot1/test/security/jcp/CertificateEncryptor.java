/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.jcp;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author qiusheng.wu
 * @Filename CertificateEncryptor.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/8 22:53</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CertificateEncryptor {

    /**
     * 【keytool实战】
     1、生成密钥对并保存到“密钥库”
     （1）生成第一个密钥对
     keytool -genkeypair -keystore test_demos.keystore -storepass storepass1223334444 -alias test1 -keyalg RSA -keypass test1keypass1223334444
     （2）生成第二个密钥对
     keytool -genkeypair -keystore test_demos.keystore -storepass storepass1223334444 -alias test2 -keyalg RSA -keypass test2keypass1223334444

     2、将“密钥库”中的证书导出
     （1）导出别名为test1的证书
     keytool -exportcert -keystore test_demos.keystore -storepass storepass1223334444 -alias test1 -file test_demos_test1.cer -rfc
     （2）导出别名为test2的证书
     keytool -exportcert -keystore test_demos.keystore -storepass storepass1223334444 -alias test2 -file test_demos_test2.cer -rfc

     3、将证书导入到另一个库中
     （1）将证书test_demos_test1.cer导入到密钥库importcert_test_demos.keystore中
     keytool -importcert -file test_demos_test1.cer -alias test1 -keystore importcert_test_demos.keystore -storepass importcertstorepass1223334444

     */

    /**
     * 类型证书X509
     */
    public static final String CERT_TYPE = "X.509";


    /**
     * 从“输入流”中获取“密钥库”KeyStore对象
     * @param in
     * @param storepass
     * @return
     * @throws KeyStoreException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public KeyStore getKeyStore(InputStream in, String storepass) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        // 实例化密钥库
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        // 加载密钥库
        keyStore.load(in, storepass.toCharArray());

        return keyStore;
    }

    /**
     * 从“密钥库”中获取“别名”为alias的证书
     * @param keyStore
     * @param alias
     * @return
     * @throws KeyStoreException
     */
    public Certificate getCertificate(KeyStore keyStore, String alias) throws KeyStoreException {
        return keyStore.getCertificate(alias);
    }

    /**
     * 从“输入流”中获取证书对象
     * @param in
     * @return
     * @throws CertificateException
     */
    public Certificate getCertificate(InputStream in) throws CertificateException {
        // 实例化证书工厂
        CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE);

        // 生成证书
        Certificate certificate = certificateFactory.generateCertificate(in);

        return certificate;
    }

    /**
     * 从“密钥库”中获取别名为alias的“私钥”
     * @param keyStore
     * @param alias
     * @param keypass
     * @return
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    public PrivateKey getPrivateKey(KeyStore keyStore, String alias, String keypass) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {

        Key key = keyStore.getKey(alias, keypass.toCharArray());

        return (PrivateKey) key;
    }

    /**
     * 从证书中获取“公钥”
     * @param certificate
     * @return
     */
    public PublicKey getPublicKey(Certificate certificate){
        // 获得公钥
        return certificate.getPublicKey();
    }

    /**
     * 加密数据
     * 注意：
     * （1）“私钥”和“公钥”均可以用来加密和解密
     * （2）用“私钥”加密，则用“公钥”解密
     * （3）用“公钥”加密，则用“私钥”解密
     * @param data 明文：待加密的数据
     * @param key 钥匙（私钥或公钥）
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public byte[] encrypt(byte[] data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 对数据加密
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 解密数据
     * 注意：
     * （1）“私钥”和“公钥”均可以用来加密和解密
     * （2）用“私钥”加密，则用“公钥”解密
     * （3）用“公钥”加密，则用“私钥”解密
     * @param data 密文：待解密的数据
     * @param key 钥匙（私钥或公钥）
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public byte[] decrypt(byte[] data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 对数据加密
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 数据加签
     * 注意：
     * 用“密钥库”中的“私钥”进行加签，用对应证书中的“公钥”进行验签。
     * @param data 原文：待加签数据
     * @param keyStore 密钥库
     * @param alis 密钥别名
     * @param keypass 密钥口令
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public byte[] sign(byte[] data, KeyStore keyStore, String alis, String keypass) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, InvalidKeyException, SignatureException {
        // 获取证书
        Certificate certificate = getCertificate(keyStore, alis);
        // 构建签名，由证书指定签名算法
        Signature signature = Signature.getInstance(((X509Certificate)certificate).getSigAlgName());

        // 获取私钥
        PrivateKey privateKey = getPrivateKey(keyStore, alis, keypass);

        // 初始化签名，由私钥构建
        signature.initSign(privateKey);

        signature.update(data);

        return signature.sign();
    }

    /**
     * 数据验签
     * 注意：
     * 用“密钥库”中的“私钥”进行加签，用对应证书中的“公钥”进行验签。
     * @param data 原文：被加签数据
     * @param sign 签名数据
     * @param certificate 证书（实际是使用了证书中的公钥）
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verify(byte[] data, byte[] sign, Certificate certificate) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        X509Certificate x509Certificate = (X509Certificate)certificate;
        // 由证书构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());

        // 由证书初始化签名，实际上是使用了证书中的公钥
        signature.initVerify(x509Certificate);

        signature.update(data);

        return signature.verify(sign);

    }

    @Test
    public void getKeyStoreTest() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);
        KeyStore keyStore = getKeyStore(in, "storepass1223334444");
        in.close();

        System.out.println(keyStore.containsAlias("test1"));
        System.out.println(keyStore.containsAlias("test2"));
        System.out.println(keyStore.containsAlias("test3"));
        System.out.println(keyStore.getProvider().getInfo());
        System.out.println(keyStore.getProvider().getName());
        System.out.println(keyStore.getProvider().getVersion());
    }

    @Test
    public void getCertificateFromKeystoreTest() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);
        KeyStore keyStore = getKeyStore(in, "storepass1223334444");
        in.close();

        Certificate certificate = getCertificate(keyStore, "test1");
        System.out.println(Base64Utils.encodeToString(certificate.getEncoded()));
        System.out.println(certificate.getType());
        System.out.println(certificate.getPublicKey().getAlgorithm());
        System.out.println(certificate.getPublicKey().getFormat());
        System.out.println(Base64Utils.encodeToString(certificate.getPublicKey().getEncoded()));

    }

    @Test
    public void getCertificateFromInputSteamTest() throws IOException, CertificateException {
        String cerPath = "classpath:test_demos_test1.cer";
        File cerFile = ResourceUtils.getFile(cerPath);

        InputStream in = new FileInputStream(cerFile);
        Certificate certificate = getCertificate(in);
        in.close();

        System.out.println(Base64Utils.encodeToString(certificate.getEncoded()));
        System.out.println(certificate.getType());
        System.out.println(certificate.getPublicKey().getAlgorithm());
        System.out.println(certificate.getPublicKey().getFormat());
        System.out.println(Base64Utils.encodeToString(certificate.getPublicKey().getEncoded()));
    }

    @Test
    public void getPrivateKeyTest() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);
        KeyStore keyStore = getKeyStore(in, "storepass1223334444");
        in.close();

        PrivateKey privateKey = getPrivateKey(keyStore, "test1", "test1keypass1223334444");
        System.out.println(privateKey.getAlgorithm());
        System.out.println(Base64Utils.encodeToString(privateKey.getEncoded()));
        System.out.println(privateKey.getFormat());
        System.out.println(privateKey.isDestroyed());
    }

    @Test
    public void getPublicKeyTest() throws IOException, CertificateException {
        String cerPath = "classpath:test_demos_test1.cer";
        File cerFile = ResourceUtils.getFile(cerPath);

        InputStream in = new FileInputStream(cerFile);
        Certificate certificate = getCertificate(in);
        in.close();

        PublicKey publicKey = getPublicKey(certificate);
        System.out.println(publicKey.getAlgorithm());
        System.out.println(publicKey.getFormat());
        System.out.println(Base64Utils.encodeToString(publicKey.getEncoded()));
    }

    @Test
    public void encryptAndDecryptTest() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);
        KeyStore keyStore = getKeyStore(in, "storepass1223334444");
        in.close();

        Certificate certificate = getCertificate(keyStore, "test1");

        PrivateKey privateKey = getPrivateKey(keyStore, "test1", "test1keypass1223334444");
        PublicKey publicKey = getPublicKey(certificate);

        // “私钥”加密，“公钥”解密
        System.out.println("::::::::::::::::::::::“私钥”加密，“公钥”解密::::::::::::::::::::::");
        byte[] data = "我有一头小毛驴，我从来也不骑。".getBytes();
        byte[] ciphertext = encrypt(data, privateKey);
        byte[] plaintext = decrypt(ciphertext, publicKey);
        System.out.println("原文："+new String(data));
        System.out.println("密文："+Base64Utils.encodeToString(ciphertext));
        System.out.println("明文："+new String(plaintext));

        // “公钥”加密，“私钥”解密
        System.out.println("::::::::::::::::::::::“公钥”加密，“私钥”解密::::::::::::::::::::::");
        data = "我有一头小毛驴，我从来也不骑。".getBytes();
        ciphertext = encrypt(data, publicKey);
        plaintext = decrypt(ciphertext, privateKey);
        System.out.println("原文："+new String(data));
        System.out.println("密文："+Base64Utils.encodeToString(ciphertext));
        System.out.println("明文："+new String(plaintext));
    }

    @Test
    public void signAndVerifyTest() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, InvalidKeyException, SignatureException {
        String keystorePath = "classpath:test_demos.keystore";
        File keystoreFile = ResourceUtils.getFile(keystorePath);

        InputStream in = new FileInputStream(keystoreFile);
        KeyStore keyStore = getKeyStore(in, "storepass1223334444");
        in.close();

        Certificate certificate = getCertificate(keyStore, "test1");
        byte[] data = "我有一头小毛驴，我从来也不骑。".getBytes();
        byte[] sign = sign(data, keyStore, "test1", "test1keypass1223334444");
        boolean result = verify(data, sign, certificate);
        System.out.println("原文："+new String(data));
        System.out.println("签名："+Base64Utils.encodeToString(sign));
        System.out.println("验签："+result);
    }
}