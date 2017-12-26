/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import javax.crypto.Cipher;
import java.security.PrivateKey;

/**
 * @author qiusheng.wu
 * @Filename RSA2Crypt.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/14 20:20</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSA2Crypt extends RSA1Crypt {

    public byte[] encrypt(byte[] data, PrivateKey privateKey) {
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        }catch (Exception e){
            return null;
        }
    }
}