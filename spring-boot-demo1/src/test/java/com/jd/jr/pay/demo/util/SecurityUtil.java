package com.jd.jr.pay.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.crypto.Cipher;

public class SecurityUtil {
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
}
