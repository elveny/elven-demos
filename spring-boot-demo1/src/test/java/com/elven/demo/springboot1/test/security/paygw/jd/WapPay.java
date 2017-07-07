/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename WapPay.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/5 13:00</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class WapPay {

    String jd_wappay_SHA256WithRSA_pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKE5N2xm3NIrXON8Zj19GNtLZ8xwEQ6uDIyrS3S03UhgBJMkGl4msfq4Xuxv6XUAN7oU1XhV3/xtabr9rXto4Ke3d6WwNbxwXnK5LSgsQc1BhT5NcXHXpGBdt7P8NMez5qGieOKqHGvT0qvjyYnYA29a8Z4wzNR7vAVHp36uD5RwIDAQAB";

    public PrivateKey toPrivateKey(String privateBase64Code) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateBase64Code));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        return privateKey;
    }

    public PublicKey toPublicKey(String publicBase64Code) throws InvalidKeySpecException, NoSuchAlgorithmException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicBase64Code));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        return publicKey;
    }


    @Test
    public void test1() throws Exception {
        String shaSource = "token=FE1B2A2DEF8F1AB6427A4019751CF4DA&tradeAmount=1&tradeCurrency=CNY&tradeDate=20170705&tradeNum=042017070512511300001009&tradeStatus=0&tradeTime=125843";
        String sign = "gtJdEpH7UOOJUqX2Q9MLJLlIdk+QOaIvEYaQSuBzLTgECCggUfHtTu+DQxzQSkUV4caJ41Rv0DAt\\nDVGpsvVhUoxtl4s5T9zmF9HZMXoZ2jnVyajde2aytAha6AyraFdeHMkAyOFPOGSvfW3fW8nX+JqO\\nh18BpdIg4+9w9lS27II=\\n";
//        sign = URLDecoder.decode(sign, "utf-8");
//        sign = sign.replaceAll(" ", "+").replaceAll("\n", "+").replaceAll("\r", "+");
//        sign = "gtJdEpH7UOOJUqX2Q9MLJLlIdk+QOaIvEYaQSuBzLTgECCggUfHtTu+DQxzQSkUV4caJ41Rv0DAt/nDVGpsvVhUoxtl4s5T9zmF9HZMXoZ2jnVyajde2aytAha6AyraFdeHMkAyOFPOGSvfW3fW8nX+JqO/nh18BpdIg4+9w9lS27IL+cg==";

        sign = sign.replace("\\n", "");
        System.out.println(sign);

        byte[] signArry = SHAUtil.decryptBASE64(sign);
        System.out.println(Base64Utils.encodeToString(signArry));

        byte[] decryptArr = new RSA1Crypt().decrypt(signArry, toPublicKey(jd_wappay_SHA256WithRSA_pub));

        String shaStr = SHAUtil.encrypt(shaSource, "SHA-256");
        String decryptStr = new String(decryptArr,"UTF-8");
        System.out.println(shaStr.equals(decryptStr));
//        RSACoder.decryptByPublicKey(signArry, jd_wappay_SHA256WithRSA_pub);
    }

    @Test
    public void wappayQueryTest(){
        byte[] bytes = SHAUtil.encrypt2Hex("11111111");
        System.out.println(new String(bytes));
        bytes = SHAUtil.encrypt2HexByte("{\"tradeNum\":\"042017070514495100001013\"}");
        System.out.println(new String(bytes));
    }

    private static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String sourceData = "{\"tradeNum\":\"042017070514495100001013\"}";
        byte[] source = new byte[0];
        //元数据
        source = sourceData.getBytes("UTF-8");

        //1.原数据byte长度
        int merchantData = source.length;
        System.out.println("原数据据:" + sourceData);
        System.out.println("原数据byte长度:" + merchantData);
        //2.计算补位
        int x = (merchantData + 4) % 8;
        int y = (x == 0) ? 0 : (8 - x);
        System.out.println("需要补位 :" + y);
        //3.将有效数据长度byte[]添加到原始byte数组的头部
        byte[] sizeByte = intToByteArray(merchantData);
        byte[] resultByte = new byte[merchantData + 4 + y];

        for(int i=0;i<4;i++){
            resultByte[i] = sizeByte[i];
        }
        //4.填充补位数据
        for (int i = 0; i < merchantData; i++) {
            resultByte[4 + i] = source[i];
        }
        for (int i = 0; i < y; i++) {
            resultByte[merchantData + 4 + i] = 0x00;
        }
        System.out.println("补位后的byte数组长度:" + resultByte.length);
        System.out.println(new String(resultByte));
    }

    @Test
    public void test3(){
        Object result = JSON.parse("{\"resultCode\":-1,\"resultMsg\":\"参数错误\",\"resultData\":null}");
        System.out.println(null != result);
    }

}