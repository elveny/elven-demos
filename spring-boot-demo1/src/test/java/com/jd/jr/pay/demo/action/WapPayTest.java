/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.jd.jr.pay.demo.action;

import com.elven.demo.springboot1.test.security.paygw.jd.RSA1Crypt;
import com.elven.demo.springboot1.test.security.paygw.jd.WapPay;
import com.jd.jr.pay.demo.action.paygw.SHAUtil;
import com.jd.jr.pay.demo.model.TradeQueryInner;
import com.jd.jr.pay.gate.signature.util.BASE64;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import com.jd.jr.pay.gate.signature.util.RsaUtil;
import com.jd.jr.pay.gate.signature.util.ThreeDesUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author qiusheng.wu
 * @Filename WapPayTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/20 11:51</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class WapPayTest {

    String merchantNum = "22294531";
    String rsaPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXf6twUqul1TATO+5nA66p2wjnRd+g96IXpfV6Sf8WXxwizGj+L19LQYRBXpZHmRh82prJ48d0FcHboCiN8pKutnuZrrKYhvORysOc5bVli0hcCn1TfYDoUWJ1UhjUQloqZKWjUz6LV9QY6bIZ1W4+Hmw6HK1bfFwUq0WzIGkJNAgMBAAECgYBlIFQeev9tP+M86TnMjBB9f/sO2wGpCIM5slIbO6n/3By3IZ7+pmsitOrDg3h0X22t/V1C7yzMkDGwa+T3Rl7ogwc4UNVj0ZQorOTx3OEPx3nP1yT3zmJ9djKaHKAmee4XmhQHdqqIuMT2XQaqatBzcsnP+Jnw/WVOsIJIqMeFAQJBAP9yq4hE+UfM/YSXZ5JR33k9RolUUq8S/elmeJIDo/3N2qDmzLjOr9iEZHxioc8JOxubtZ0BxA+NdfKz4v0BSpkCQQC2RIrAPRj9vOk6GfT9W1hbJ4GdnzTb+4vp3RDQQ3x9JGXzWFlg8xJT1rNgM8R95Gkxn3KGnYHJQTLlCsIy2FnVAkAWXolM3pVhxz6wHL4SHx9Ns6L4payz7hrUFIgcaTs0H5G0o2FsEZVuhXFzPwPiaHGHomQOAriTkBSzEzOeaj2JAkEAtYUFefZfETQ2QbrgFgIGuKFboJKRnhOif8G9oOvU6vx43CS8vqTVN9G2yrRDl+0GJnlZIV9zhe78tMZGKUT2EQJAHQawBKGlXlMe49Fo24yOy5DvKeohobjYqzJAtbqaAH7iIQTpOZx91zUcL/yG4dWS6r+wGO7Z1RKpupOJLKG3lA==";
    String desKey = "ta4E/aspLA3lgFGKmNDNRYU92RkZ4w2t";
    String rsaPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKE5N2xm3NIrXON8Zj19GNtLZ8xwEQ6uDIyrS3S03UhgBJMkGl4msfq4Xuxv6XUAN7oU1XhV3/xtabr9rXto4Ke3d6WwNbxwXnK5LSgsQc1BhT5NcXHXpGBdt7P8NMez5qGieOKqHGvT0qvjyYnYA29a8Z4wzNR7vAVHp36uD5RwIDAQAB";

    @Test
    public void jdEncryptTest(){
        byte[] key = BASE64.decode(desKey);
        System.out.println("密文："+ThreeDesUtil.encrypt2HexStr(key, "我有一只小毛驴我从来也不骑"));
    }

    @Test
    public void paygwEncryptTest(){
        System.out.println("密文："+ SHAUtil.byte2HexLowerCase(encrypt(SHAUtil.encrypt2Hex("我有一只小毛驴我从来也不骑"), desKey)));
    }

    public byte [] encrypt(byte[] data, String key){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, getEncryptKey(Base64.decodeBase64(key.getBytes())));
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE,getDecryptKey(Base64.decodeBase64(desKey.getBytes())));
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public Key getEncryptKey(byte [] keyBytes) {
        return  new SecretKeySpec(keyBytes, "DESede");
    }

    public Key getDecryptKey(byte[] keyBytes) {
        return  new SecretKeySpec(keyBytes, "DESede");
    }

    @Test
    public void payorder(){
        System.out.println(SHAUtil.byte2HexLowerCase(encrypt(SHAUtil.encrypt2Hex("180"), desKey)));
        System.out.println(ThreeDesUtil.encrypt2HexStr(BASE64.decode(desKey), "180"));
    }

    @Test
    public void asynNotifyTest() throws Exception {

        /////////////////////////////京东////////////////////////////////////////////
        String encrypt = "MWYxMjBjMzViZjgwOWM5ZDhjNjc0YmY1ZWJlY2QyODU0YTc5NmQ3ZWQxMWU1NzE3MWQ0OTUwOGI5NzllYmE4ZjM1YzRiZjlmYWE1M2ZiYjVmYzBmYTgyMDYyM2Q0YjM0NGM1ODFkZDhlYTA2Mjk0ZDE5ZDBlZDk5NTc3MmE4Nzk4OTFlYjIwZDgzMTc4MDU3NGVkZTFjNDY0MDMzNzNjZjc2OWZiMDQ0YjVhZGNhYmRhMGZmYTkyNzRhZDNhM2IxOGY5ZjZhYjBmYjhmZmI3Yzg0OTA3YzM0OGJmZTYwZTIzNzM3YjVmYzMzNmNkYTE0MjM2OWIwZDM5MjI2YWM5YmY3ZmZjZDBkNWJmM2ZkYWY4YTU3OWU4MDE3ZjQ5YmQ0ZWIyMDA0NTFmODZkNmViMDBiMDE2YTU3NTNjMzJjNDIzNWI5ZDkyYzQ3OTU4OTc2ZGIyZmNiMGUxNGRjNTM2OGZjYjQ0NmE0YWY1ZWVjZDYzNWI5ZDkyYzQ3OTU4OTc2NmIwM2QyZTU1ODJlNDNjM2M1NjA2YmQ5ZDc3MTRkMmNjN2ZiMDM3Yzg5ZDk1ODFkMWJhZmVjYjUwMzJlNTdkMTFmN2QxMDAxNjgyMzJjNTZhMmQzNTcyZGE4OTUzYWFjNTU5MDY4YWYyODE5ZDcyNmY5NmE1YTBmYWFiZTRiZTQ2OGZhMmM4M2JjMGM5NmNiMDE3ZWQ4MDkxY2FjZThiNzg4MjY5OWY1ZTJlYzBjOTIxODBhOGExNjExNGY4NWQwM2NkZjI2MTFmM2VmODcxYWM3MjUxZjMxMzZlYjFmNzI1NWE0OWM4MjMxZGY1MzBmY2Y1Mjg2NGUzMWRlMjc0M2I5ZDM5NjQzN2ZmZWQ1Y2M5NDY4ZDcwNWM1YzVhZmRlYzYwZWU3MDVhNjE0N2I1MGVlM2UyMGE2MzExNTE4YTUxOGRjMzBmMmUxZjE2NzYzNGRiNDJlODFmMDczOGYzZjMxN2NkMjkzNmU4ODc3NzJjMjkzM2ZlODlmMjUyNDVmNDI2MDA0M2VkYmUwOTlkNGEyNjU3YTM5YTE4ODU2OTBmNGQyNDcwZDE0ZWRjMmQxYjgxMzhhNjA5M2ZlNDkxYTQyMzE5YzBlNTA0MTdkYTg2ZGQ2NDQwODBmMjM4ZGI2YzIzMjNhOTE0M2VmMjZiZjczN2M5NWQwODYxMWY2OGE5MDQ0ZDZmNzE0NmIxZjQwZDdmZDMxOTQ2ZDM3YjIwNDJiODUzZGM0NTk0MzM5YzJkN2M2NDdiNGM4MzQ4MTRjZTIxZTlmYTYzNDYxNGMxMjlhZTE3NjE0ZDIzM2Q2MTQ4YzJiNWE3ZWVjMDU5MjFmNzJkNGNjNTU1NWZkNzVhN2U5Y2I1MDU1NjhlMWRlNjVhNzkyOGUxMThlODQyMGJkNzE2NjdmMDc3YmEyYTFkNmQyOTFiOGNjZTU2ZGMyYmE3ODY5ZGZiNmMyMWViYjc2ODc0Y2I3YTc4NGQ5NWY2NjY2Y2E5NjI0N2I1MGE4MTliMDBkNGIzNmViZTJlY2JmYTcwODUzYTM5ZTcwMDVmYWEzNWY2MDFhMWM2MGQ1MzEyYmQxNDU3Zjg4ZWVhNzY2YjZhOGE4ZGMxMGY3NjYwOWEzNWY2MDFhMWM2MGQ1MzFhNzA4NTNhMzllNzAwNWZhYTYxMmJmNjJiMmFlMGY5ODMxMzQ0MzQ0NjMxZDc3MTUyY2FiMjZlMjcyYmJjYmQzODVmNDY4OTA5YTdjMjlmNTI5NWFlZjE3NTI4ZmE4MzVhNzA4NTNhMzllNzAwNWZhNDk5OTQ2ZGU0OGU0NGQ2ZTE4YmRiYTBjZjNhM2ZkNjY5ODJjNGVhZjQzMjIyYWFhMWM0ZmU1ODRiNTg5OWEwYzAwNjI2NTllMDZkYzhiYTVmMjI3ZjUyYmQ3MjcyODllZmEwYzhiNDIwODc4ZjUzODY1MzAzZDkyNDM5OTRkNDczMTBjZDBhMTc4ZjAwOTIyZmM2ODk5YjkyYTJiODcwNjU4MzkzMzJkZWYzNDY1MzJlYTNiYTFhNjM0MWIwNjM4NjBjNjlmMzg1NWZjZWM5YWExMDdjZWY1MjkwZTZjMzgzOGYxNTRiNzFlN2E1YTczYWFkNzJlOTRiOWI3MmI2YWYyMTJjMjQ5Y2UzMmUxMGI4YWE0N2YzYzFmNjNiOGY4NjJlZmU1ZDM5NjcwODA3MGNjY2JjYWFkYjM3NzBmMGQzYjIyMGFmZTE3YWNjZWU1N2RmZTQxMzAxYjA2MDdlMg==";
//        String reqBody = XmlEncryptUtil.decrypt(rsaPubKey, desKey, encrypt);
        String reqBody = ThreeDesUtil.decrypt4HexStr(RsaUtil.decryptBASE64(desKey), new String(Base64.decodeBase64(encrypt), "UTF-8"));
        System.out.println(reqBody);

        /////////////////////////////paygw////////////////////////////////////////////
        byte[] hexSourceData = SHAUtil.hex2byte(new String(Base64.decodeBase64(encrypt), "UTF-8").getBytes());
        byte[] unDesResult = decrypt(hexSourceData, desKey);
        String decrypt = SHAUtil.decrypt4HexStr(unDesResult);
        System.out.println(decrypt);
    }

    @Test
    public void query() throws IllegalAccessException, UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
        /////////////////////////////京东////////////////////////////////////////////
        TradeQueryInner queryTradeDTO = new TradeQueryInner();
        queryTradeDTO.setVersion("V2.0");
        queryTradeDTO.setMerchant("22294531");
        queryTradeDTO.setTradeNum("123456");
        queryTradeDTO.setTradeType("0"); // 0:消费 1：退款
        String xml = JdPayUtil.genReqXml(queryTradeDTO, rsaPrivateKey, desKey);
        System.out.println(xml);

        /////////////////////////////paygw////////////////////////////////////////////
        String plain = "<?xml version=\"1.0\" encoding=\"utf-8\"?><jdpay><version>V2.0</version><merchant>22294531</merchant><tradeNum>123456</tradeNum><oTradeNum></oTradeNum><tradeType>0</tradeType><cert></cert></jdpay>";
        String sign = Base64Utils.encodeToString(new RSA1Crypt().encrypt(SHAUtil.encrypt(plain, "SHA-256").getBytes("UTF-8"), new WapPay().toPrivateKey(rsaPrivateKey)));
        String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?><jdpay><version>V2.0</version><merchant>22294531</merchant><tradeNum>123456</tradeNum><oTradeNum></oTradeNum><tradeType>0</tradeType><sign>"+sign+"</sign><cert></cert></jdpay>";
        String encrypt = Base64.encodeBase64String(SHAUtil.byte2HexLowerCase(encrypt(SHAUtil.encrypt2Hex(data), desKey)).getBytes("UTF-8"));
        System.out.println(encrypt);
    }
}