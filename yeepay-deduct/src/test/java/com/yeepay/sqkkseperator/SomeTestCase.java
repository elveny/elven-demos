/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.yeepay.sqkkseperator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeepay.g3.facade.yop.ca.dto.DigitalSignatureDTO;
import com.yeepay.g3.facade.yop.ca.enums.CertTypeEnum;
import com.yeepay.g3.facade.yop.ca.enums.DigestAlgEnum;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.frame.yop.ca.rsa.RSA;
import com.yeepay.g3.frame.yop.ca.rsa.RSAKeyUtils;
import com.yeepay.g3.frame.yop.ca.utils.Encodes;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.http.HttpUtils;
import com.yeepay.g3.sdk.yop.utils.DateUtils;
import com.yeepay.shade.com.google.common.base.Charsets;
import com.yeepay.shade.com.google.common.base.Joiner;
import com.yeepay.shade.com.google.common.collect.Lists;
import com.yeepay.shade.com.google.common.collect.Maps;
import com.yeepay.shade.com.google.common.collect.Sets;
import com.yeepay.shade.org.apache.commons.lang3.StringUtils;
import com.yeepay.shade.org.springframework.util.Base64Utils;
import com.yeepay.shade.org.springframework.util.LinkedMultiValueMap;
import com.yeepay.shade.org.springframework.util.MultiValueMap;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * @author qiusheng.wu
 * @Filename SomeTestCase.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/11/22 18:47</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SomeTestCase {

    String isv_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCxvbcO80BpbiirZUOtTOUfTQroEpbT6cSpG1EJ+wfjzc/N85FiMfIpnUFPdsZOT+tiMljVdV8JWjz/QtvhxWY4wwkReymXzxaIusxfslWH7h32qnJ0UP7TgjvDRmewKJrOSmHUS1ImWp4qb1X6DU4wxdP7c/OYaqkljBpJpVqJ4UU9DcDCzsyWwdq8i7IJmMbP6OYt8KGEfeQdkbdxUqNZnxvi25uCRgniUpcApfXFptONNCF/1/7qU3X0Vb4RG6KleZo9YnqP2YSwrRm9TgZ7MfxjDKyZbTP9Pe31bwNf7gfLLgUu4r4OGyNssR1aD648HfM3rDjclJepC1AiT+n7AgMBAAECggEBAIra+mr8kGKNQ8p6pv95Zjoo1w9sjlZpd1DhXVMdwREv1VtBIGAFQvlNuBsbYFsHxo0FZi8ErcVBsQt+MQdVTsGjZK86d8j4aNNSk51jVbyGwvPUPuwt5pZYVNX+Z7zQS/hDVeAjI7+A5bWjGxjpUh96PVxhPnnhQdlqdZL5Uh0KCP7BmFQ9NSW6EotSrbWVDbWW6Bzke9xnEYa7h2QkdwrzitF+HjkuwQsAgAAsN2bMzkn65fpYTI9LH18KBID1763WBlKC5UYB7FtyAr/xEZM6IW1V2S5VPQjmsQLqDKUQAvpJYtwQIUqrFSCYaluB1FwRncrgiQFp3mKYmClxGJECgYEA5XDlR5nuoOr+lMVWSYi+hRbUGgoxWmx2EBHchTRdLmDIW5CwuKOtGD3tq84OXjmZq2q28j2rZdOICeYwuYysGQkVoSPs6KqjoX8hDeBwYYOlni0zvvzajZ2Zs1Ws620X23u+/rodMXv403PrL1PSMa1z8XMQ3T3G6TpvGwXzzJMCgYEAxlDHGdf4rdvbjWuKL0kxUmzSWP0HR8T6tBIz4MsNZDbxMWNUA3BLnwv5OLxb18sSU5EPauxmsdJR4uqxJIXnTTsZoDR9GyskELez7RenIXkSgynqb5kmk3FJyaTRZqtoLP0ioMRUCkTwzrSy+YKOgkC+IMQhVgpcdc27beWOtfkCgYEAgrqBmPtnEL70URXjJUDJtREdxrx6BFh6KIvUxvzf/tXcMvfNCVcRABgA/HwKibtuCFJkbL+gkgMlpuZGauJxCE65yTer+6GGXDUQQ1TXE2isC+Ubb72oVTov4hN11CozhrYKTB8FFTXav7hzj2LGB9IZlsAGItjZJfhKZ+5LTAsCgYB3NzYJVih7M3CvehdOx7wrpZlpv2nx/fsL5uli2A3L0a96lhB6JLaA/Oyr66d2ePAiZlCTYVt2yE1LkPQ+VXSvm7iS8xrGC1AZ8KTsAU0KNUMosDjrL3DeL7tAyaDMDHyKlv2LEZrHtZvhVwVEMvTCXnMtNVoo4/+jHbLvpJD+2QKBgQCvoKXxCapsov1gpBpYANIUsdeoBqvhn/RwGHZj+7CMJPr6CDv2dGm6mh4F11tiwmwOO2wVwaO65LR4KOPiRoYOL+nGBAuPAQqz3jhfAAcBMKGXKIq+XWskb/ZrZgvc4hYUyt3WoAWoK1zfao3C9MA3lNh+ojpN6B0qmPMOwjSQnw==";
    String yop_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6p0XWjscY+gsyqKRhw9MeLsEmhFdBRhT2emOck/F1Omw38ZWhJxh9kDfs5HzFJMrVozgU+SJFDONxs8UB0wMILKRmqfLcfClG9MyCNuJkkfm0HFQv1hRGdOvZPXj3Bckuwa7FrEXBRYUhK7vJ40afumspthmse6bs6mZxNn/mALZ2X07uznOrrc2rk41Y2HftduxZw6T4EmtWuN2x4CZ8gwSyPAW5ZzZJLQ6tZDojBK4GZTAGhnn3bg5bBsBlw2+FLkCQBuDsJVsFPiGh/b6K/+zGTvWyUcu+LUj2MejYQELDO3i2vQXVDk7lVi2/TcUYefvIcssnzsfCfjaorxsuwIDAQAB";
    String appKey="SQKK10000469946";

//    @Test
    public void test1(){
        String timestamp = DateUtils.formatCompressedIso8601Timestamp((new Date()).getTime());
        System.out.println(timestamp);
    }

//    @Test
    public void test2() throws UnsupportedEncodingException {
        String apiUri = "https://open.yeepay.com/yop-center/rest/v1.0/paperorder/api/pay/batchtempcard/order";
        String canonicalURI = HttpUtils.getCanonicalURIPath(apiUri);
        System.out.println(canonicalURI);

        System.out.println(URLEncoder.encode(apiUri, "UTF-8"));
    }

//    @Test
    public void test3() {
        Map<String, String> map = new HashMap<>();
        map.put("111", "111");
        map.put("222", "222");
        System.out.println(YopClient3.getCanonicalQueryString(map, true));

    }

    @Test
    public void test4() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
//        String timestamp = DateUtils.formatCompressedIso8601Timestamp((new Date()).getTime());
        String timestamp = "20171122T135747Z";
        System.out.println("timestamp====="+timestamp);

        String apiUri = "https://open.yeepay.com/yop-center/rest/v1.0/paperorder/api/pay/batchtempcard/order";
        String canonicalURI = HttpUtils.getCanonicalURIPath(apiUri);
        System.out.println("canonicalURI====="+canonicalURI);

        Map<String, String> map = new HashMap<>();
        map.put("111", "111");
        map.put("222", "222");
        String canonicalQueryString = YopClient3.getCanonicalQueryString(map, true);


        MultiValueMap<String, String> headers = new LinkedMultiValueMap();

//        String requestId = UUID.randomUUID().toString();
        String requestId = "122333444455555";
        headers.add("x-yop-request-id", requestId);
        headers.add("x-yop-date", timestamp);
        headers.add("x-yop-appkey", appKey);
        String authString = "yop-auth-v2/" + appKey + "/" + timestamp + "/" + "1800";
        Set<String> headersToSignSet = new HashSet();
        headersToSignSet.add("x-yop-request-id");
        headersToSignSet.add("x-yop-date");
        headersToSignSet.add("x-yop-appkey");

        SortedMap<String, String> headersToSign = getHeadersToSign(headers, headersToSignSet);
        System.out.println("headersToSign=====\n"+headersToSign);
        String canonicalHeader = getCanonicalHeaders(headersToSign);
        System.out.println("canonicalHeader====="+canonicalHeader);

        String signedHeaders = "";
        if (headersToSignSet != null) {
            signedHeaders = signedHeaderStringJoiner.join(headersToSign.keySet());
            signedHeaders = signedHeaders.trim().toLowerCase();
        }
        System.out.println("signedHeaders====="+signedHeaders);

        String canonicalRequest = authString + "\nPOST\n" + canonicalURI + "\n" + canonicalQueryString + "\n" + canonicalHeader;
        System.out.println("canonicalRequest=====\n"+canonicalRequest);

        PrivateKey isvPrivateKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
        DigitalSignatureDTO digitalSignatureDTO = new DigitalSignatureDTO();
        digitalSignatureDTO.setPlainText(canonicalRequest);
        digitalSignatureDTO.setCertType(CertTypeEnum.RSA2048);
        digitalSignatureDTO.setDigestAlg(DigestAlgEnum.SHA256);
        digitalSignatureDTO = DigitalEnvelopeUtils.sign(digitalSignatureDTO, isvPrivateKey);
        System.out.println("Signature=====\n"+digitalSignatureDTO.getSignature());


        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(isvPrivateKey);
        signature.update(canonicalRequest.getBytes("UTF-8"));
        byte[] result = signature.sign();
        String Signature = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(result);
        System.out.println("Signature1====="+Signature);

    }

    @Test
    public void test5() throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-yop-appkey", appKey);
        headers.put("x-yop-date", "20171122T135747Z");
        headers.put("x-yop-request-id", "122333444455555");

        Map<String, Object> params = new HashMap<>();
        params.put("111", "111");
        params.put("222", "222");

        String canonicalRequest = YeepayUtils.buildPlainText(headers, params, "https://open.yeepay.com/yop-center/rest/v1.0/paperorder/api/pay/batchtempcard/order");
        System.out.println("canonicalRequest=====\n"+canonicalRequest);

        PrivateKey isvPrivateKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(isvPrivateKey);
        signature.update(canonicalRequest.getBytes("UTF-8"));
        byte[] result = signature.sign();
        String Signature = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(result);
        System.out.println("Signature====="+Signature);

    }

//    @Test
    public void test6() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PrivateKey isvPrivateKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
    }

    @Test
    public void test7() throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, UnsupportedEncodingException {
        String content = "{\n" +
                "\"state\" : \"SUCCESS\",\n" +
                "\"result\" : {\n" +
                "\"status\" : \"PROCESSING\",\n" +
                "\"merchantno\" : \"10000469946\",\n" +
                "\"requestno\" : \"first20171122_06100699\",\n" +
                "\"codesender\" : \"NONE\"\n" +
                "},\n" +
                "\"ts\" : 1511318876863,\n" +
                "\"sign\" : \"ykzzJr30N8WWm5J5WYwaNKV6Cx4Icd19gFYlUgsqJCNCFk6uYuE0RsJuZVFJvA9iKrp-As4duApGJ68mGFB5-PF1wgnTx9W7BrPwNgDQD6gXZwy1W4pk0wYpB9qtWMKVlLCQW_0yekbzQsWmIpc2Hj1FUKbvVBxbI-cYp6rdHT66kSAreHAvadaaJhDfb05Bg_RkswJe3bTEFmp2gjKbKZ7iFo8uSTMs37hDVjltGF5At3b9edJQPnf2XOA-bUoEbYYH74YftHKGL66cTXHZfnkJYNK6GNIekdeWqWAcsisnUFYh5H5ORPx1_k_aozpjPuYe0jlEXq6MmeLKPDM43g$SHA256\"\n" +
                "}";

        String strResult = getBizResult(content);

        JSONObject jsonObject = (JSONObject) JSON.parse(content);
//        JSONObject jsonObject = (JSONObject) JSON.parse(content, Feature.OrderedField);
        String sign = jsonObject.getString("sign");
        System.out.println(sign);
        sign = sign.substring(0, sign.indexOf("$"));
        System.out.println(sign);
        String result = jsonObject.getString("result");
        System.out.println(result);
        System.out.println(strResult);

//        String ziped = result.replaceAll("[ \t\n]", "");
        String ziped = strResult.replaceAll("[ \t\n]", "");
        ziped = StringUtils.trimToEmpty(ziped);

        Signature signature = Signature.getInstance("SHA256withRSA");
        PublicKey publicKey = RSAKeyUtils.string2PublicKey(yop_public_key);
//        PublicKey publicKey = toPublicKey(org.apache.commons.codec.binary.Base64.decodeBase64(yop_public_key));
        signature.initVerify(publicKey);
        signature.update(ziped.getBytes("UTF-8"));
        byte[] signByte = org.apache.commons.codec.binary.Base64.decodeBase64(sign);
        boolean flag = signature.verify(signByte);
        System.out.println(flag);

    }

    @Test
    public void test8() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PublicKey publicKey = RSAKeyUtils.string2PublicKey(yop_public_key);
        PublicKey publicKey1 = toPublicKey(org.apache.commons.codec.binary.Base64.decodeBase64(yop_public_key));
        System.out.println(Base64Utils.encodeToString(publicKey.getEncoded()).equals(Base64Utils.encodeToString(publicKey1.getEncoded())));
    }

    @Test
    public void test9() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PrivateKey isvPrivateKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
        String source = "yop-auth-v2/SQKK10000469946/20171124T135026Z/1800\n" +
                "POST\n" +
                "/https%3A//open.yeepay.com/yop-center/rest/v1.0/paperorder/api/pay/batchtempcard/order\n" +
                "appKey=SQKK10000469946&batchdetails=                [{\"requestno\":\"0000000000000000\",\"bathprotocolno\":\"$bathprotocolno\",\"amount\":\"1.01\",\"productname\":\"产品经理\",\"avaliabletime\":\"产品经理\",\"cardno\":\"6222020111122220000\",\"username\":\"张易宝\",\"phone\":\"18516147564\",\"idcardno\":\"320301198502169142\"},{\"requestno\":\"0000000000000001\",\"bathprotocolno\":\"$bathprotocolno\",\"amount\":\"1.01\",\"productname\":\"产品经理\",\"avaliabletime\":\"产品经理\",\"cardno\":\"6222020111122220000\",\"username\":\"张易宝\",\"phone\":\"18516147564\",\"idcardno\":\"320301198502169142\"},{\"requestno\":\"201711230000000000000002\",\"bathprotocolno\":\"$bathprotocolno\",\"amount\":\"1.01\",\"productname\":\"产品经理\",\"avaliabletime\":\"产品经理\",\"cardno\":\"6222020111122220000\",\"username\":\"张易宝\",\"phone\":\"18516147564\",\"idcardno\":\"320301198502169142\"},{\"requestno\":\"201711230000000000000003\",\"bathprotocolno\":\"$bathprotocolno\",\"amount\":\"1.01\",\"productname\":\"产品经理\",\"avaliabletime\":\"产品经理\",\"cardno\":\"6222020111122220000\",\"username\":\"张易宝\",\"phone\":\"18516147564\",\"idcardno\":\"320301198502169142\"}]&merchantbatchno=2017112405001&merchantno=10000469946&requesttime=2017-11-24 13:50:26&terminalno=SQKKSCENEPK010\n" +
                "x-yop-appkey:SQKK10000469946\n" +
                "x-yop-date:20171124T135026Z\n" +
                "x-yop-request-id:2017112405001";
        byte[] data = source.getBytes(Charsets.UTF_8);
        byte[] sign = RSA.sign(data, isvPrivateKey, DigestAlgEnum.SHA256);
        String signToBase64 = Encodes.encodeUrlSafeBase64(sign);
        StringBuilder cipherText = new StringBuilder();
        cipherText.append(signToBase64);
        cipherText.append("$");
        cipherText.append(DigestAlgEnum.SHA256.getValue());
        System.out.println(cipherText.toString());
    }

    @Test
    public void test10() throws UnsupportedEncodingException {
        String date = "2017-11-24 19:35:44";
        System.out.println(URLEncoder.encode(date, "UTF-8"));
        System.out.println(URLEncoder.encode(URLEncoder.encode(date, "UTF-8"), "UTF-8"));
        System.out.println(URLEncoder.encode(date, "GBK"));
        System.out.println(HttpUtils.normalizePath(date));
    }

    /**
     * 提取公钥
     * */
    protected PublicKey toPublicKey(byte [] key){
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        // KEY_ALGORITHM 指定的加密算法
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBizResult(String content) {
        if (com.yeepay.shade.org.apache.commons.lang3.StringUtils.isBlank(content)) {
            return content;
        } else {
            String jsonStr = com.yeepay.shade.org.apache.commons.lang3.StringUtils.substringAfter(content, "\"result\" : ");
            jsonStr = com.yeepay.shade.org.apache.commons.lang3.StringUtils.substringBeforeLast(jsonStr, "\"ts\"");
            jsonStr = StringUtils.substringBeforeLast(jsonStr, ",");
            return jsonStr;
        }
    }

    private static SortedMap<String, String> getHeadersToSign(MultiValueMap<String, String> headers, Set<String> headersToSign) {
        SortedMap<String, String> ret = Maps.newTreeMap();
        String key;
        if (headersToSign != null) {
            Set<String> tempSet = Sets.newHashSet();
            Iterator var4 = ((Set)headersToSign).iterator();

            while(var4.hasNext()) {
                key = (String)var4.next();
                tempSet.add(key.trim().toLowerCase());
            }

            headersToSign = tempSet;
        }

        Iterator var6 = headers.entrySet().iterator();

        while(true) {
            Map.Entry entry;
            do {
                do {
                    do {
                        if (!var6.hasNext()) {
                            return ret;
                        }

                        entry = (Map.Entry)var6.next();
                        key = (String)entry.getKey();
                    } while(entry.getValue() == null);
                } while(((List)entry.getValue()).isEmpty());
            } while((headersToSign != null || !isDefaultHeaderToSign(key)) && (headersToSign == null || !((Set)headersToSign).contains(key.toLowerCase()) || "Authorization".equalsIgnoreCase(key)));

            ret.put(key, (String) ((List)entry.getValue()).get(0));
        }
    }

    private static final Set<String> defaultHeadersToSign = Sets.newHashSet();
    private static final Joiner headerJoiner = Joiner.on('\n');
    private static final Joiner signedHeaderStringJoiner = Joiner.on(';');
    static {
        defaultHeadersToSign.add("Host".toLowerCase());
        defaultHeadersToSign.add("Content-Length".toLowerCase());
        defaultHeadersToSign.add("Content-Type".toLowerCase());
        defaultHeadersToSign.add("Content-MD5".toLowerCase());
    }
    private static boolean isDefaultHeaderToSign(String header) {
        header = header.trim().toLowerCase();
        return header.startsWith("x-yop-") || defaultHeadersToSign.contains(header);
    }

    private static String getCanonicalHeaders(SortedMap<String, String> headers) {
        if (headers.isEmpty()) {
            return "";
        } else {
            List<String> headerStrings = Lists.newArrayList();
            Iterator var2 = headers.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var2.next();
                String key = (String)entry.getKey();
                if (key != null) {
                    String value = (String)entry.getValue();
                    if (value == null) {
                        value = "";
                    }

                    headerStrings.add(HttpUtils.normalize(key.trim().toLowerCase()) + ':' + HttpUtils.normalize(value.trim()));
                }
            }

            Collections.sort(headerStrings);
            return headerJoiner.join(headerStrings);
        }
    }


}