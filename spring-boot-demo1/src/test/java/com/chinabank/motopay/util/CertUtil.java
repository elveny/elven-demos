package com.chinabank.motopay.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

import com.chinabank.motopay.service.SecurityPayService;


public class CertUtil {
	public static boolean veryfyCert(String text, String Sign,String publicCert,String charset) throws Exception{
		X509Certificate cert = SecurityUtil.readX509Cert(publicCert);
        cert.checkValidity(new Date());//验证有效期

        //读取跟证书
        //X509Certificate ca = SecurityUtil.readX509Cert("key/casub.cer");
        //不抛出异常就是根证书颁发的
        //cert.verify(ca.getPublicKey());
        System.out.println("根CA验证通过...");
        //System.out.println("cert:" + cert);
        
        Signature signature = Signature.getInstance("SHA1withRSA");
        
        //AKS反向流程验签
        //X509Certificate cert_aks = SecurityUtil.readX509Cert("gn_npp_11_002.cer");

        byte[] plain = SecurityUtil.rsaDecrypt(Base64.decode(Sign), cert.getPublicKey());

        byte[] HASH_PREFIX_SHA256 = {48, 81, 48, 13, 6, 9, 96, -122, 72, 1, 101, 3, 4, 2, 1, 5, 0, 4, 64};

        byte[] cipher = IOUtil.joinBytes(HASH_PREFIX_SHA256, SecurityUtil.sha256(text.getBytes()));

        boolean checkResult = Arrays.equals(plain, cipher);
        System.out.println("check Result : " + Arrays.equals(plain, cipher));
        
        //验签
        //signature.initVerify(cert);
        //signature.update(text.getBytes(charset));
        //System.out.println("sign length:" + Base64.decode(Sign).length);
        //byte[] bb = new byte[128];
        //boolean b = signature.verify(bb);
        //System.out.println("yanqianjieguo ::" + b);

        return checkResult;
        //return signature.verify(Base64.decode(Sign));

	}
	
	public static String certSign(String text, String privateKeyFileName, String passWord,String charset) throws Exception{
		Signature signature = Signature.getInstance("SHA1withRSA");
		KeyStore ks = SecurityUtil.readPKCS12(privateKeyFileName, passWord);
		
		System.out.println();
		text = text.replace("\r", "").replace("\n", "");
		signature.initSign((PrivateKey) ks.getKey("com.jdpay.offline.keycontainer", passWord.toCharArray()));
        signature.update(text.getBytes(charset));
        System.out.println("text:" + text + "charset:" + charset);
        System.out.println("text length:" + text.length());
        System.out.println("text hashcode:" + text.hashCode());
        byte[] signed = signature.sign();
        System.out.println("signed length: " + signed);
        String signBase64 = Base64.encode(signed).replace("\r", "").replace("\n", "");
        System.out.println("sign:" + signBase64);
        System.out.println("sign hashCode:" + Base64.encode(signed).hashCode());
        System.out.println("sign length:" + signBase64.length());
		return signBase64;
	}
	
	public static void main(String[] args) throws Exception {
		X509Certificate cert = SecurityUtil.readX509Cert("key/xinpingtai.cer");
        
        cert.checkValidity(new Date());//验证有效期

        //读取跟证书
        //X509Certificate ca = SecurityUtil.readX509Cert("com/wangyin/commons/util/certs/npp/wangyin_user_ca.cer");
        //不抛出异常就是根证书颁发的
        //cert.verify(ca.getPublicKey());
        System.out.println("根CA验证通过...");

        //模拟商户私钥签名
        String text = "123";//签名内容
        Signature signature = Signature.getInstance("SHA1withRSA");
        KeyStore ks = SecurityUtil.readPKCS12("key/cert.pfx", "123456");
        signature.initSign((PrivateKey) ks.getKey("1", "123456".toCharArray()));
        signature.update(text.getBytes());
        byte[] signed = signature.sign();
        
        String base64 = certSign(text,"key/test.pfx","123456","UTF-8");
        //模拟延签
        signature.initVerify(cert);
        signature.update(text.getBytes());
        boolean verified = signature.verify(Base64.decode(base64));
        
       
        
        System.out.println("验签结果:" + verified);
	}
	
	
	
	
}
