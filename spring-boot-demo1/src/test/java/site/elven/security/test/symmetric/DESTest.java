/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.security.test.symmetric;

import org.junit.Test;
import site.elven.security.test.common.utils.SecurityUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @author qiusheng.wu
 * @Filename DESTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/2 16:06</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DESTest {
    @Test
    public void test1() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        // 加密算法
        String algorithm = "DES";
        // 密钥长度
        int keySize = 56;
        // 生成对称密钥
        Key key = SecurityUtils.genKey(algorithm, keySize);

        byte[] encryptData = SecurityUtils.encrypt(algorithm, key, "我有一头小毛驴我从来也不骑".getBytes());

        byte[] decryptData = SecurityUtils.decrypt(algorithm, key, encryptData);

        System.out.println(new String(decryptData));

    }

}