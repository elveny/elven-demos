/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.security.asymmetric;

import org.junit.Test;
import site.elven.test.security.common.utils.SecurityUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/**
 * @author qiusheng.wu
 * @Filename DSATest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/4 9:30</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DSATest {

    @Test
    public void encryptTest1() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        // 加密算法
        String algorithm = "DSA";
        // 密钥长度
        int keySize = 1024;

        // 生成密钥对
        KeyPair keyPair = SecurityUtils.genKeyPair(algorithm, keySize);

        // 注意：encryptStep不能大于keySize/8-11（最好等于keySize/8-11，可以减少加密次数，提高加密效率）。否则加密会抛异常（javax.crypto.IllegalBlockSizeException: Data must not be longer than xxx bytes //xxx为具体长度）
        int encryptStep = keySize/8-11;
        byte[] encryptData = SecurityUtils.encrypt(algorithm, keyPair.getPublic(), "我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑".getBytes(), encryptStep);
        System.out.println("密文长度："+encryptData.length);

        // 注意：decryptStep必须固定为keySize/8，否则解密会抛异常（javax.crypto.BadPaddingException: Decryption error）
        int decryptStep = keySize/8;
        byte[] clearData = SecurityUtils.decrypt(algorithm, keyPair.getPrivate(), encryptData, decryptStep);
        System.out.println("明文长度："+clearData.length);
        System.out.println("明文："+new String(clearData));
    }

    @Test
    public void signTest1() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        // 加密算法
        String algorithm = "DSA";
        String signAlgorithm = "DSA";
        // 密钥长度
        int keySize = 1024;

        // 生成密钥对
        KeyPair keyPair = SecurityUtils.genKeyPair(algorithm, keySize);

        byte[] sign = SecurityUtils.sign(signAlgorithm, keyPair.getPrivate(), "我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑".getBytes());

        boolean result = SecurityUtils.verify(signAlgorithm, keyPair.getPublic(), "我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑我有一头小毛驴我从来也不骑".getBytes(), sign);

        System.out.println(result);
    }
}