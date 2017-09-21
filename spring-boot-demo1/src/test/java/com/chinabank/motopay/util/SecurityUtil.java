package com.chinabank.motopay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.crypto.Cipher;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class SecurityUtil extends DigestUtils{
	/**
     * 加密模式
     */
    public static final int ENCRYPT_MODE = Cipher.ENCRYPT_MODE;
    /**
     * 解密模式
     */
    public static final int DECRYPT_MODE = Cipher.DECRYPT_MODE;
    /**
     * 默认加密模式：ECB（电子密码本）
     */
    private static final String DEFAULT_MODE = "ECB";
    /**
     * 默认对称加密填充算法
     */
    private static final String DEFAULT_SYM_PADDING = "PKCS5Padding";
    /**
     * 默认非对称加密填充算法
     */
    private static final String DEFAULT_ASYM_PADDING = "PKCS1Padding";
    
    /**
     * 效率：以文件名为Key缓存证书
     */
    private static final ConcurrentMap<String, X509Certificate> certs = new ConcurrentHashMap<String, X509Certificate>();
    
    /**
     * 效率：以文件名为Key缓存KeyStore
     */
    private static final ConcurrentMap<String, KeyStore> keyStores = new ConcurrentHashMap<String, KeyStore>();
    
    /**
     * 读取X509标准的证书,不保证每个文件只读取一次，初始化并发读取可能读取多次
     *
     * @param filename
     *            证书文件
     * @return X509Certificate
     * @throws GeneralSecurityException
     */
    public static final X509Certificate readX509Cert(String filename)
            throws IOException, GeneralSecurityException {
        X509Certificate cached = certs.get(filename);
        if (cached != null) {// 文件已经读取过，则直接返回上次结果
            return cached;
        }
        InputStream is = null;
        try {
        	File certFile = new File(filename);
            is = new FileInputStream(certFile);
            X509Certificate created = readX509Cert(is);
            certs.putIfAbsent(filename, created);
            return created;
        } finally {
        	is.close();
        }
    }
    
    /**
     * 读取X509标准的证书
     *
     * @param is
     *            输入流
     * @return X509Certificate
     * @throws GeneralSecurityException
     */
    public static final X509Certificate readX509Cert(InputStream is)
            throws GeneralSecurityException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate interCert = (X509Certificate) cf
                .generateCertificate(is);
        return interCert;
    }
    
    /**
     * 读取KeyStore
     *
     * @param filename
     *            KeyStore文件名
     * @param pwd
     *            KeyStore 密码
     * @param ksType
     *            KeyStore类型（JKS|JceKS|PKCS12|BKS|UBER），一般为JKS
     * @return KeyStore密钥库
     * @throws IOException
     *             读取文件异常时抛出
     * @throws GeneralSecurityException
     *             读取密钥失败时抛出
     */
    public static final KeyStore readKeyStore(String filename, String pwd,
            String ksType) throws IOException, GeneralSecurityException {
        KeyStore cached = keyStores.get(filename);
        if (cached != null) {// 文件已经读取过，则直接返回上次结果
            return cached;
        }
        InputStream fis = null;
        try {
        	File keyFile = new File(filename);
            fis = new FileInputStream(keyFile);
            KeyStore created = readKeyStore(fis, pwd, ksType);
            keyStores.putIfAbsent(filename, created);
            return created;
        } finally {
            if(fis != null){
            	fis.close();
            }
        }
    }
    
    /**
     * 将流转换成KeyStore
     *
     * @param is
     *            输入流
     * @param pwd
     *            KeyStore 密码
     * @param ksType
     *            KeyStore类型（JKS|JceKS|PKCS12|BKS|UBER），一般为JKS
     * @return KeyStore密钥库
     * @throws IOException
     *             读取文件异常时抛出
     * @throws GeneralSecurityException
     *             读取密钥失败时抛出
     */
    public static final KeyStore readKeyStore(InputStream is, String pwd,
            String ksType) throws IOException, GeneralSecurityException {
        KeyStore store = KeyStore.getInstance(ksType);
        try {
            store.load(is, pwd.toCharArray());
        } finally {
        	if(is != null){
        		is.close();
        	}
        	
        }
        return store;
    }
    
    /**
     * @see #readKeyStore( InputStream, String, String)
     */
    public static final KeyStore readPKCS12(InputStream is, String pwd)
            throws IOException, GeneralSecurityException {
        return readKeyStore(is, pwd, "PKCS12");
    }
    
    /**
     * @see #readKeyStore(String, String, String)
     */
    public static final KeyStore readPKCS12(String filename, String pwd)
            throws IOException, GeneralSecurityException {
        return readKeyStore(filename, pwd, "PKCS12");
    }
    
    private static byte[] asymCipher(byte[] data, Key key, int opmode,
            String alg) throws GeneralSecurityException,
            IllegalArgumentException {
        Validate.notNull(key, "密钥不能为空");
        Validate.notNull(alg, "加解密算法不能为空");
        return cipher(data, key, opmode, alg);
    }

    /**
     * RSA加密
     *
     * @param bplain
     *            明文
     * @param rsaKey
     *            RSA密钥
     * @param mode
     *            加解密模式(ECB|CBC|CFB|OFB)
     * @param padding
     *            填充算法(PKCS1Padding|PKCS5Padding|PKCS7Padding...)
     * @return 加密结果,字节数组形式
     * @throws GeneralSecurityException
     *             加解密出现错误时抛出
     * @throws IllegalArgumentException
     *             参数不合法时抛出
     */
    public static byte[] rsaEncrypt(byte[] bplain, Key rsaKey, String mode,
            String padding) throws GeneralSecurityException,
            IllegalArgumentException {
        String alg = String.format("RSA/%s/%s", mode, padding);
        return asymCipher(bplain, rsaKey, ENCRYPT_MODE, alg);
    }

    /**
     * 使用默认ECB模式进行RSA加密
     *
     * @param bplain
     *            明文
     * @param rsaKey
     *            RSA密钥
     * @param padding
     *            填充算法(PKCS1Padding|PKCS5Padding|PKCS7Padding...)
     * @return 加密结果,字节数组形式
     * @throws GeneralSecurityException
     *             加解密出现错误时抛出
     * @throws IllegalArgumentException
     *             参数不合法时抛出
     */
    public static byte[] rsaEncrypt(byte[] bplain, Key rsaKey, String padding)
            throws GeneralSecurityException, IllegalArgumentException {
        return rsaEncrypt(bplain, rsaKey, DEFAULT_MODE, padding);
    }

    /**
     * 使用默认ECB模式,PKCS1Padding填充算法进行RSA加密
     *
     * @param bplain
     *            明文
     * @param rsaKey
     *            RSA密钥
     * @return 加密结果,字节数组形式
     * @throws GeneralSecurityException
     *             加解密出现错误时抛出
     * @throws IllegalArgumentException
     *             参数不合法时抛出
     */
    public static byte[] rsaEncrypt(byte[] bplain, Key rsaKey)
            throws GeneralSecurityException, IllegalArgumentException {
        return rsaEncrypt(bplain, rsaKey, DEFAULT_MODE, DEFAULT_ASYM_PADDING);
    }

    /**
     * RSA解密
     *
     * @param bcipher
     *            密文
     * @param rsaKey
     *            RSA密钥
     * @param mode
     *            加解密模式(ECB|CBC|CFB|OFB)
     * @param padding
     *            填充算法(PKCS1Padding|PKCS5Padding|PKCS7Padding...)
     * @return 解密结果,字节数组形式
     * @throws GeneralSecurityException
     *             加解密出现错误时抛出
     * @throws IllegalArgumentException
     *             参数不合法时抛出
     */
    public static byte[] rsaDecrypt(byte[] bcipher, Key rsaKey, String mode,
            String padding) throws GeneralSecurityException,
            IllegalArgumentException {
        String alg = String.format("RSA/%s/%s", mode, padding);
        return asymCipher(bcipher, rsaKey, DECRYPT_MODE, alg);
    }

    /**
     * 使用默认的ECB模式进行RSA解密
     *
     * @see #rsaDecrypt(byte[], Key, String, String)
     */
    public static byte[] rsaDecrypt(byte[] bcipher, Key rsaKey, String padding)
            throws GeneralSecurityException, IllegalArgumentException {
        return rsaDecrypt(bcipher, rsaKey, DEFAULT_MODE, padding);
    }

    /**
     * 使用默认的ECB模式,PKCS1Padding填充算法进行RSA解密
     *
     * @see #rsaDecrypt(byte[], Key, String, String)
     */
    public static byte[] rsaDecrypt(byte[] bcipher, Key rsaKey)
            throws GeneralSecurityException, IllegalArgumentException {
        return rsaDecrypt(bcipher, rsaKey, DEFAULT_MODE, DEFAULT_ASYM_PADDING);
    }
    
    
    /**
     * 通用加解密方式
     * 
     * @param data
     *            原文
     * @param key
     *            密钥
     * @param opmode
     *            模式（ENCODE_MODE|DECRYPT_MODE）
     * @param alg
     *            算法：算法/模式/填充算法，如DES/ECB/PKCS5Padding
     * @return 加密/解密结果。字节数组形式
     * @throws GeneralSecurityException
     *             加解密出现错误时抛出
     * @throws IllegalArgumentException
     *             参数不合法时抛出
     */
    public static byte[] cipher(byte[] data, Key key, int opmode, String alg)
            throws GeneralSecurityException, IllegalArgumentException {
        if (ArrayUtils.isEmpty(data)) {
            return data;// 无需加解密
        }
        Validate.isTrue(opmode == ENCRYPT_MODE || opmode == DECRYPT_MODE,
                "加密|解密");
        Validate.notNull(alg, "加解密算法不能为空");
        Cipher c1 = Cipher.getInstance(alg);
        c1.init(opmode, Validate.notNull(key, "empty key!"));// validate and init
        byte[] result = c1.doFinal(data);
        return result;
    }
    




}
