/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import sun.security.util.Password;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * @Filename CipherTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-21 下午11:43</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CipherTest {
    /**
     * 密钥算法 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥
     */
    public static final String KEY_ALGORITHM = "DES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    /**
     * 转换密钥
     *
     * @param key
     *            二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    public Key toKey(String key) throws Exception
    {

        // 实例化DES密钥材料
        DESKeySpec dks = new DESKeySpec(key.getBytes());

        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

        // 生成秘密密钥
        SecretKey secretKey = keyFactory.generateSecret(dks);

        return secretKey;
    }

    @Test
    public void singleStep() throws Exception {
        System.out.println("///////////////////////////////singleStep/////////////////////////////////////////");

        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        byte[] cipherText = cipher.doFinal(originalText.getBytes());
        System.out.println("密文："+Base64Utils.encodeToString(cipherText));

        ///////////////////////////////Cipher解密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：解密操作
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("明文："+new String(plainText));
    }

    @Test
    public void multiplesStep() throws Exception {

        System.out.println("///////////////////////////////multiplesStep/////////////////////////////////////////");

        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：单步加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        byte[] cipherText1 = cipher.update(originalText.getBytes());
        byte[] cipherText2 = cipher.doFinal();

        byte[] cipherText = ByteUtils.concat(cipherText1, cipherText2);

        System.out.println("密文："+Base64Utils.encodeToString(cipherText));

        ///////////////////////////////Cipher解密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：单步解密操作
        byte[] plainText1 = cipher.update(cipherText);
        byte[] plainText2 = cipher.doFinal();
        byte[] plainText = ByteUtils.concat(plainText1, plainText2);

        System.out.println("明文："+new String(plainText));
    }

    @Test
    public void multiplesStep1() throws Exception {

        System.out.println("///////////////////////////////multiplesStep1/////////////////////////////////////////");

        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：多步加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        byte[] cipherText1 = cipher.update(originalText.getBytes(), 0, 10);
        byte[] cipherText2 = cipher.update(originalText.getBytes(), 10, originalText.getBytes().length-10);
        byte[] cipherText3 = cipher.doFinal();

        byte[] cipherText = ByteUtils.concat(cipherText1, cipherText2);
        cipherText = ByteUtils.concat(cipherText, cipherText3);

        System.out.println("密文："+Base64Utils.encodeToString(cipherText));

        ///////////////////////////////Cipher解密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：多步解密操作
        byte[] plainText1 = cipher.update(cipherText, 0, 10);
        byte[] plainText2 = cipher.update(cipherText, 10, cipherText.length-10);
        byte[] plainText3 = cipher.doFinal();

        byte[] plainText = ByteUtils.concat(plainText1, plainText2);
        plainText = ByteUtils.concat(plainText, plainText3);

        System.out.println("明文："+new String(plainText));
    }

    @Test
    public void multiplesStep2() throws Exception {

        System.out.println("///////////////////////////////multiplesStep2/////////////////////////////////////////");

        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：灵活多步加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        byte[] originalBytes = originalText.getBytes("UTF-8");

        byte[] cipherText = multiples(cipher, originalBytes, 3);

        System.out.println("密文："+Base64Utils.encodeToString(cipherText));

        ///////////////////////////////Cipher解密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：灵活多步解密操作
        byte[] plainText = multiples(cipher, cipherText, 3);

        System.out.println("明文："+new String(plainText, "UTF-8"));
    }

    /**
     * 灵活多步加密/解密
     * @param cipher Cipher对象
     * @param in 输入的字节码（明文/密文）
     * @param step 步长
     * @return out 加密/解密后的字节码
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public byte[] multiples(Cipher cipher, byte[] in, int step) throws BadPaddingException, IllegalBlockSizeException {
        byte[] out = null;

        // 根据步长进行“多步”加密
        int i = 0;
        int length = in.length;
        System.out.println("in.length："+length);
        while (i < length){
            if(length - i < step){
                step = length - i;
            }

            // 第N步加密
            byte[] temp = cipher.update(in, i, step);
            if(ObjectUtils.isEmpty(out)){
                out = temp;
            }
            else{
                out = ByteUtils.concat(out, temp);
            }
            i += step;
        }

        // 步骤四：调用doFinal()
        byte[] temp = cipher.doFinal();
        out = ByteUtils.concat(out, temp);

        return out;
    }

    @Test
    public void wrapKeyTest() throws Exception {

        ///////////////////////////////Cipher wrap/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为wrap模式
        cipher.init(Cipher.WRAP_MODE, toKey("11111111"));

        // 步骤三：wrap密钥
        Key key = toKey("12345678");
        System.out.println("key:"+Base64Utils.encodeToString(key.getEncoded()));
        String algorithm = key.getAlgorithm();
        byte[] wrappedKey = cipher.wrap(key);
        System.out.println("wrapKey:"+Base64Utils.encodeToString(wrappedKey));

        ///////////////////////////////Cipher unwrap/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为wrap模式
        cipher.init(Cipher.UNWRAP_MODE, toKey("11111111"));

        // 步骤三：unwrap密钥
        Key unwrappedKey = cipher.unwrap(wrappedKey, algorithm, Cipher.SECRET_KEY);
        System.out.println("unwrappedKey:"+Base64Utils.encodeToString(unwrappedKey.getEncoded()));

    }

    @Test
    public void getParametersTest() throws Exception {
        System.out.println("///////////////////////////////getParameters/////////////////////////////////////////");

        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        byte[] cipherText = cipher.doFinal(originalText.getBytes());
        System.out.println("密文："+Base64Utils.encodeToString(cipherText));

        // 步骤四：获取AlgorithmParameters
        AlgorithmParameters algParams = cipher.getParameters();
        System.out.println("algParams:"+algParams);


        ///////////////////////////////Cipher解密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：解密操作
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("明文："+new String(plainText));

        // 步骤四：获取AlgorithmParameters
        AlgorithmParameters algParams1 = cipher.getParameters();
        System.out.println("algParams1:"+algParams1);

        int outputSize = cipher.getOutputSize(1);
        System.out.println("outputSize:"+outputSize);
    }

    @Test
    public void getOutputSizeTest() throws Exception {
        System.out.println("///////////////////////////////getParameters/////////////////////////////////////////");

        ///////////////////////////////Cipher加密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：加密操作
        String originalText =  "我有一只小毛驴我从来也不骑";
        System.out.println("原文："+originalText);
        byte[] cipherText = cipher.doFinal(originalText.getBytes());
        System.out.println("密文："+Base64Utils.encodeToString(cipherText));

        // 步骤四：获取OutputSize
        int outputSize = cipher.getOutputSize(1024);
        System.out.println("outputSize:"+outputSize);

        int outputSize1 = cipher.getOutputSize(2048);
        System.out.println("outputSize1:"+outputSize1);


        ///////////////////////////////Cipher解密/////////////////////////////////////////
        // 步骤一：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤二：初始化Cipher对象为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：解密操作
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("明文："+new String(plainText));

        // 步骤四：获取AlgorithmParameters
        AlgorithmParameters algParams1 = cipher.getParameters();
        System.out.println("algParams1:"+algParams1);

    }

    @Test
    public void AESTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 步骤一：生成密钥
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey aesKey = keygen.generateKey();

        // 步骤二：加密
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

        String cleartext = "我有一只小毛驴我从来也不骑";
        System.out.println("cleartext::::"+cleartext);

        byte[] ciphertext = aesCipher.doFinal(cleartext.getBytes());
        System.out.println("ciphertext::::"+Base64Utils.encodeToString(ciphertext));

        // 步骤三：解密
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] plaintext = aesCipher.doFinal(ciphertext);
        System.out.println("plaintext::::"+new String(plaintext));
    }

    @Test
    public void PBETest() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {

        PBEKeySpec pbeKeySpec;
        PBEParameterSpec pbeParamSpec;
        SecretKeyFactory keyFac;

        // Salt
        byte[] salt = {
                (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
        };

        // Iteration count
        int count = 1000;

        // Create PBE parameter set
        pbeParamSpec = new PBEParameterSpec(salt, count);

        // Prompt user for encryption password.
        // Collect user password as char array (using the
        // "readPassword" method from above), and convert
        // it into a SecretKey object, using a PBE key
        // factory.
        System.out.print("Enter encryption password:  ");
        System.out.flush();
        pbeKeySpec = new PBEKeySpec(Password.readPassword(System.in));
//        pbeKeySpec = new PBEKeySpec("111111".toCharArray());
        keyFac = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_256");
        SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

        // Create PBE Cipher
        Cipher pbeCipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_256");

        // Initialize PBE Cipher with key and parameters
        pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

        // Our cleartext
        byte[] cleartext = "This is another example".getBytes();

        // Encrypt the cleartext
        byte[] ciphertext = pbeCipher.doFinal(cleartext);

    }

    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        new CipherTest().PBETest();
    }
}