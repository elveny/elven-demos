/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;


import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;


/**
 * @author qigang.chen
 * @Filename KeyUtils.java
 * @description  证书操作工具类 用于从证书中提取公钥、私钥等
 * @Version 1.0
 * @History <li>Author: qigang.chen</li>
 * <li>Date: 2017/2/28 14:05</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KeyUtils {

    /**
     * 提取证书公钥
     * */
    public static byte [] getPublicKey(byte [] cerBytes) throws Exception{
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate c = cf.generateCertificate(new ByteArrayInputStream(cerBytes));
        return c.getPublicKey().getEncoded();
    }

    /**
     * 提取证书私钥
     * *
    public static byte [] getPrivateKey(byte [] keyStoreBytes , String storePass, String keyAlias , String keyPass) throws Exception{
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new ByteArrayInputStream(keyStoreBytes) , storePass.toCharArray());
        PrivateKey caprk = (PrivateKey)ks.getKey(keyAlias,keyPass.toCharArray());
        return caprk.getEncoded();

    }
     */

    /**
     * 解析证书 ， 提取证书秘钥
     */
    public static byte [] parse(CertificateType type , byte[] certBytes ,String storePass , String aliase , String keyPass) {
       try{
           KeyStore ks = null;
           switch (type){
               case DER:
                   return getPublicKey(certBytes);
               case  JKS :
                   ks = KeyStore.getInstance("JKS");
                   break;
               case PKCS12:
                   ks = KeyStore.getInstance("PKCS12");
                   break;
           }
           ks.load(new ByteArrayInputStream(certBytes) , storePass.toCharArray());
           if(ks.isCertificateEntry(aliase)){
               return ks.getCertificate(aliase).getPublicKey().getEncoded();
           }
           if(ks.isKeyEntry(aliase)){
               Key key = ks.getKey(aliase , keyPass.toCharArray());
               return key.getEncoded();
           }
           return null;
       }catch (Exception e){
           throw new RuntimeException("证书解析失败",e);
       }

    }
//
//    public static void main(String [] args) throws Exception{
//        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDkxsAvqzpxB08a/DnwWw7goZR7n9IXJYacRvMkbXGhf2oxNcrsih8Ghxqz1EfNuDSf3YNLhd1a1LeKHfUql6JRJeHMkXOQchT6cHpQgBdMT8i3VU8Op+ubWyo6oJS6gHMhdu1MXsclHOjXx8excQd1FaRig+mNd7gzDd9iZIwvfQIDAQAB";
//        String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOTGwC+rOnEHTxr8OfBbDuChlHuf0hclhpxG8yRtcaF/ajE1yuyKHwaHGrPUR824NJ/dg0uF3VrUt4od9SqXolEl4cyRc5ByFPpwelCAF0xPyLdVTw6n65tbKjqglLqAcyF27UxexyUc6NfHx7FxB3UVpGKD6Y13uDMN32JkjC99AgMBAAECgYBZnaFbCu/xprNOFs97X7AMPj8zzf82IfUA8qn78wM4B/lJHZFH5zMQE4xaNSWLaET0+oib5Ow40Gq1J9cJwCxMb6jM9y2KLCF77jn2W2fYmZpg90BQod8/qrZw5TyTGfvoEEfTV+7GmsjM1Rl2/doiltCVsmN3fl2ndZkjm2TnAQJBAPheyqzs03jQNd2BeePhCkhAHqWcyVxwM8KpJpfRlHJWNmdpv0szCRzN1UYggz0oRBtGPodfVqYl/Qo1L7akbDkCQQDrzd7fE5vFN+xikbj1vPWCmQEtF0SOGIwdB8iHoXre+bd72aww/p/anezogDWMfcfnbeQi5n7q43G73F1k02VlAkEAk7yW5Euu8rMvB0ZIAdPPsPBrCmn7oL+hGk5RhYcFvQkpLVRKlGmUJw0Mqr0WM5+q+2pKMW31dbktDhBbBFJDiQJBAOaD3ANRhO7YS6LUd+7q6UrhTlAJNgB/KMHccQF+L8K4ddGCKp2+3QB3cDid1TthXsH0IJipoZr3+6A9vaMqMS0CQGSbg8v5QAOUVh3lKY4R0survnrFZImUoh/nUwm6fBH8Qc6Ple86R9Zfly1jzywPNMIeWZD2gxyQsEc9GXhYFvA=";
//        KeyObj ko = new KeyObj();
//        ko.setType(CertificateType.TEXT);
//        ko.setKeyBytes(priKey.getBytes());
//
//        String str = "111111111111";
//
//        byte [] bytes = SecurityFactory.getCrypt("RSA1" , ko).encrypt(str.getBytes());
//
//
//        ko = new KeyObj();
//        ko.setType(CertificateType.TEXT);
//        ko.setKeyBytes(pubKey.getBytes());
//
//        System.out.println(new String(SecurityFactory.getCrypt("RSA1" , ko).decrypt(bytes)));
//    }


}