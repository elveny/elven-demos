/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;

/**
 * @author qiusheng.wu
 * @Filename RSA3Crypt.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/14 20:22</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSA3Crypt extends RSA1Crypt {

    protected byte [] doFinal(Cipher cipher , byte[] data  , int maxBlock) throws Exception{
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > maxBlock) {
                cache = cipher.update(data, offSet, maxBlock);
            } else {
                cache = cipher.update(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxBlock;
        }
        cache = cipher.doFinal();
        out.write(cache, 0, cache.length);
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
}