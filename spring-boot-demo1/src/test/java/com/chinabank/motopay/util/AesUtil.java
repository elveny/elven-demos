package com.chinabank.motopay.util;

import java.security.Key;  
import java.security.SecureRandom;  
  


import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.SecretKey;  


public class AesUtil {
	public static String DES = "AES"; // optional value AES/DES/DESede  
    
    public static String CIPHER_ALGORITHM = "AES"; // optional value AES/DES/DESede  
      
    public static int KEY_SIZE = 128; //AES使用长度为128位的秘钥
  
    public static Key getSecretKey(String key) throws Exception{  
        SecretKey securekey = null;  
        if(key == null){  
            key = "";  
        }  
        
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(Base64.decode(key));
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);
        //使用128位长度的秘钥
        keyGenerator.init(KEY_SIZE,random);  
        securekey = keyGenerator.generateKey();  
        return securekey;  
    }  
      
    public static String encrypt(String data,String key,String charset) throws Exception {  
        SecureRandom sr = new SecureRandom();  
        Key securekey = getSecretKey(key);  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        byte[] bt = cipher.doFinal(data.getBytes(charset));  
        String strs = Base64.encode(bt);  
        return strs.replace("\n", "").replace("\r", "");  
    }  
      
      
    public static String detrypt(String message,String key,String charset) throws Exception{  
        SecureRandom sr = new SecureRandom();  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        Key securekey = getSecretKey(key);  
        cipher.init(Cipher.DECRYPT_MODE, securekey,sr); 
        System.out.println("key: " + key);
        System.out.println("charset: " + charset);
        System.out.println("message: " + message);
        System.out.println("message length: " + message.length());
        System.out.println("message hashcode: " + message.hashCode());
        byte[] res = Base64.decode(message.replace("\r", "").replace("\r", ""));  
        res = cipher.doFinal(res);  
        return new String(res,charset);  
    }  
      
    public static void main(String[] args)throws Exception{  
        String message = "password";  
        String key = "";  
        String entryptedMsg = encrypt(message,key,"UTF-8");  
        System.out.println("encrypted message is below :");  
        System.out.println(entryptedMsg);  
          
        String decryptedMsg = detrypt(entryptedMsg,key,"UTF-8");  
        System.out.println("decrypted message is below :");  
        System.out.println(decryptedMsg);  
    }  
}
