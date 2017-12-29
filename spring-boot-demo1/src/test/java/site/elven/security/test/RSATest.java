/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.security.test;

import org.junit.Test;
import org.springframework.data.redis.util.ByteUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * @author qiusheng.wu
 * @Filename RSATest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/29 10:31</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSATest {

    @Test
    public void test1() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 加密算法
        String algorithm = "RSA";
        // 密钥长度
        int keySize = 1024;

        // 生成密钥对
        KeyPair keyPair = genKeyPair(algorithm, keySize);

        // 创建cipher
        Cipher cipher = Cipher.getInstance(algorithm);

        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        // 注意：encryptStep不能大于keySize/8-11（最好等于keySize/8-11，可以减少加密次数，提高加密效率）。否则加密会抛异常（javax.crypto.IllegalBlockSizeException: Data must not be longer than xxx bytes //xxx为具体长度）
        int encryptStep = keySize/8-11;
        byte[] encryptData = multiples(cipher, "我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑".getBytes(), encryptStep);
        System.out.println("密文长度："+encryptData.length);

        // 解密
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        // 注意：decryptStep必须固定为keySize/8，否则解密会抛异常（javax.crypto.BadPaddingException: Decryption error）
        int decryptStep = keySize/8;
        byte[] clearData = multiples(cipher, encryptData, decryptStep);
        System.out.println("明文长度："+clearData.length);
        System.out.println("明文："+new String(clearData));

    }

    /**
     * 生成密钥对
     * @param algorithm 算法
     * @param keysize 密钥长度
     * @return 密钥对
     * @throws NoSuchAlgorithmException
     */
    public KeyPair genKeyPair(String algorithm, int keysize) throws NoSuchAlgorithmException {
        // 步骤一：创建
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

        // 步骤二：初始化
        keyPairGenerator.initialize(keysize);

        // 步骤三：生成KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        return keyPair;
    }

    /**
     * 灵活多步加密/解密
     * 注意：加密时，step必须小于keySize/8-11，解密时的step必须固定为keySize/8
     * @param cipher Cipher对象
     * @param in 输入的字节码（明文/密文）
     * @param step 步长
     * @return out 加密/解密后的字节码
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public byte[] multiples(Cipher cipher, byte[] in, int step) throws BadPaddingException, IllegalBlockSizeException {
        byte[] out = new byte[0];

        // 根据步长进行“多步”加密
        int i = 0;
        int length = in.length;
        System.out.println("in.length："+length);
        while (i < length){
            if(length - i < step){
                step = length - i;
            }

            // 第N步加密
            byte[] temp = cipher.doFinal(in, i, step);
            out = ByteUtils.concat(out, temp);
            i += step;
        }

        return out;
    }

}