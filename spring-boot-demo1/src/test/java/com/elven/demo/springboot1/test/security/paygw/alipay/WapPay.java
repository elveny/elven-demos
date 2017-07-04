/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.alipay;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename WapPay.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/30 10:14</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class WapPay {

    String alipayWapPayPrivate = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEnCz5OV5Gz1OMTxsSnr9eV06XanYdlHdI/NPmBdrQ21MJ5vzHR4uAIrF/ceQ8P5U4/6D+X1pryMKAL4DSdG1N2Qs/P6fPfmbszL8uxRa4YjDuEnnzSVDcNL6EgYWxKg3KxZS5fbL7pUHk2Q/xUM0p3Sp0JUqsIZfPStF34+roXuB8wOMpiWph/EIGufGvT037zIJYfvSWRtRep2GX8M5YULtnZ0hYIaivocjZdezZTKaBWbfoRpDvHbu89eYDOhhfuPohC7ZDGMp9BB+LLYTZH9STWoKD4Mu1r5kbOKegLfzh1LPqZhfoWnBN+xwOz0hkQjlmcCAsRcPb9G+QYKHPAgMBAAECggEAMQ9ufA3DWUMez8v8YhklB4wZYV9br3nb377ZzkHgQGDECefRIRDRBWjQbiv+mmOTm3DHaRRx6WpuuunYKQQ15BUmWhH8zYEgJv9gw7bavwBkxc9EGsPHDJDe4zrf8TlzvM74RN/CNeNfy4Caq1aPcKuIJ0wcoi4TcfdmI4rIv4rB+EtZ4D7oUrA22T0OT1fj3B5y7Bf8d0/uHAZiYuAuJIQj0620nklGfPIGsY/iI5FjSC4GB4JjkM9mfYE/YPWFt3MeYekzJaecu/md7tQCJ7IkSTXsuyw0Nq5mwgizqWGVQ95V+FWg7GF4rv8JBL2IbfYCcwW7VGiHCouEKli4MQKBgQC8jm7if9Gez0bwyvWoIjCvy1jt0cWDHOmqyFjj9lqjghP6Ajjye6uTV7/pVSPXgesIbREFRIRInxZ4cjIUQ5QimVwOJOA/miwcrm9viaOZymbMnKWALcwrNYX6bxrNGfZq/SwPMeUBikARAgGJSFi/hU2YCkWKAtNZVav5YLw74wKBgQC0CuYNIE1Wx6SDXugAL92F4jXNY2hJABtIq/I6Czp3kXpOu4MCzv1kZw9XcasYHQpVGpWKOhXqRDa9snObqoWaEzg48fmIMu+8nT+Aw0lqtlMJaA9up39WSVuWmeCYw/MfjRH0fkSjN5Tm/StuHmNOUbM6G9JAPF6COjw6PJk+JQKBgQCM1MFjMN6rA9vTe3PR2X+NvY61SgmArTallTA3S8hU+ZHcGI7aJZ531YGEUFAN5X5bc8LTsWIwL4qD+Z8H3ZWl5e+IrOhE+OzbmzmmECJ+elsflhxSyWIGH9rqutNEv5B6RpAC5VxLMyw4x8vH8p4+pdzXo0xfEU80GTXyc9sDywKBgQCAA3fLQXp8B3ubxPFkoKP7u/nRTasqrJ65rziNuMoblh7GuGDQsGhO6VBpNnMWtB9Sn5JQNDi81z+s5aN5vWc9RAknxDVOeBkpnQ03+qLK81RbTdgbL5FEwugSTtr5A+psNpvboaQgATcofj5RD95AI6sx1/JIpiP0qH+GUOwM2QKBgE/AuhaS+Rw4MnUswXTC4ELw7nhA27KI6aKPx0pvqPV6ylQ0+wt8HPj18yvJfITIx+8xXxkqQ38k9911CatkHoDwe+2bDvnopScVAq+HEepwdgFze5NtZ/zRS1qXhWnq+AlIoG0/zHR1J7puzqUX/h8j2sRj+x6LSqCRM7g6c2sY";
    String alipayWapPayPublic = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhJws+TleRs9TjE8bEp6/XldOl2p2HZR3SPzT5gXa0NtTCeb8x0eLgCKxf3HkPD+VOP+g/l9aa8jCgC+A0nRtTdkLPz+nz35m7My/LsUWuGIw7hJ580lQ3DS+hIGFsSoNysWUuX2y+6VB5NkP8VDNKd0qdCVKrCGXz0rRd+Pq6F7gfMDjKYlqYfxCBrnxr09N+8yCWH70lkbUXqdhl/DOWFC7Z2dIWCGor6HI2XXs2UymgVm36EaQ7x27vPXmAzoYX7j6IQu2QxjKfQQfiy2E2R/Uk1qCg+DLta+ZGzinoC384dSz6mYX6FpwTfscDs9IZEI5ZnAgLEXD2/RvkGChzwIDAQAB";
    String alipayPublic = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm1DrqcwXo+SBTt6Qs8iHBT2Bznq0tQHCDroNdidR/dZAAHgZ9OBDVIOQqTtwJ3O2hVendQvh/UmjcYrgvhh7gmkWMdnVEkZbEp/Paks8Gt0cR4EUZA7M4xCMms3uiWvZuKUSpw9fMP7WS1s6JRXaSH5edb9vxHBINiuPMsgMpZ15LhBRkHzCHk0bad3x0neZvvSPxCn1jqZ/ch7mxtifnh/7rP+QrkKdsgxQSrVeeD2dRLVwf8REjFTM38G2WrELlisYJGekc/5FfECTREeV37c6ryzzcLicLDir8d7pKHAD+NMkIs5nzP/TBxE0uhpeJlmoN/JaoqMhynVSkxmDcQIDAQAB";

    String data = "app_id=2017062907596262&biz_content={\"subject\":\"马上消费支付宝网关支付\",\"out_trade_no\":\"042017063011270300000018\",\"total_amount\":\"390.96\",\"product_code\":\"QUICK_WAP_WAY\"}&charset=utf-8&format=JSON&method=alipay.trade.wap.pay&notify_url=http://receptionapidev.msxf.com/paygw/alipay/wapPayAsyNotify&return_url=http://receptionapidev.msxf.com/paygw/alipay/wapPaySynNotify&sign_type=RSA2&timestamp=2017-06-30 11:27:04&version=1.0";
    String sign = "fKmxoFztNocpvIrqalp9z3w9h7Mlu2Xa61rDWRiVjWaPl4fjOC3cVH90Mx8pRs5/vcw4FNwnGYYB6IyXzO9ZfN8+P/5tMPF92BEMyWzLhJbVl+DGUYSiaf/3ApjZWDUPah+XlnmVIo3+r+Uey8AvghYwoO4lbVnC24htPJZPWZWEmRFAxT4amdCwklzFp+I8J5sujnfGl6EJNwpd5ig5i0v0Nzzi8ZqX3mobebn6hPDXHneE3beTqI350H2xfeeRmT+3qgiOkRSWHALoE1u+Q9gTkuwvewzh/Hu1R+WDoMSfkxe8pWmC3yuQdwzJ4pfc/Y7rsnkmkASvgEwPl43BMA==";

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
    public void signTest() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        PrivateKey privateKey = toPrivateKey(alipayWapPayPrivate);

        Signature signature = Signature.getInstance("SHA256withRSA");

        signature.initSign(privateKey);

        signature.update(data.getBytes());

        byte[] signs = signature.sign();

        String signBaseCode = Base64Utils.encodeToString(signs);

        System.out.println(signBaseCode);

        System.out.println(StringUtils.pathEquals(signBaseCode, sign));

    }

    @Test
    public void verifyTest() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        PublicKey publicKey = toPublicKey(alipayWapPayPublic);

        Signature signature = Signature.getInstance("SHA256withRSA");

        signature.initVerify(publicKey);

        signature.update(data.getBytes());

        boolean verify = signature.verify(Base64Utils.decodeFromString(sign));

        System.out.println("verify:"+verify);
    }

    @Test
    public void urlencodeTest() throws UnsupportedEncodingException {
        String dateTime = "2017-06-30 11:57:44";
        System.out.println(URLEncoder.encode("JSON", "utf-8"));
        System.out.println(URLEncoder.encode(dateTime));
        System.out.println(URLEncoder.encode("biz_content={\"subject\":\"马上消费支付宝网关支付\",\"out_trade_no\":\"042017063016215000004007\",\"total_amount\":\"390.96\",\"product_code\":\"QUICK_WAP_WAY\"}"));

        System.out.println(URLDecoder.decode("%3D"));
        System.out.println(URLDecoder.decode("2017-06-30 14:45:28"));

        System.out.println(URLEncoder.encode("FVjZ3QFlfVIR6mkF9yhPZamq9lTp+QbJOv+vdLwVs0FDyzr5XK7RDgjsQV55BzvHDWRgrPGiaMu6Z9BGMvX9lRaZB8kKwM0dplKvVrAA+VB6vTNDekbd2dM5zgtRACEBVAG+8n5LpGuc2fTt7CD6W+SrcluQkV6HbwtA7/6YMIDDiCm2IncLJ9dNzy+XF9mkD1oMfoFxBcxU2K/aKSpY/Qg87YXb6XHLjEIALXTWmVFflpZ07yn+Xz4pEH6kfktP9xl/l8jpLZgFc6M0JN1CYgTrV1g1L9EME/6Jbp0hFwROpVX148tZNQBR/UraXrNEBsQL8A6a9OaYDJfddZL60g==", "utf-8"));

    }

    @Test
    public void wappayQueryVerify() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        PublicKey publicKey = toPublicKey(alipayPublic);

        Signature signature = Signature.getInstance("SHA256withRSA");

        signature.initVerify(publicKey);

        signature.update("{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.TRADE_NOT_EXIST\",\"sub_msg\":\"交易不存在\",\"buyer_pay_amount\":\"0.00\",\"invoice_amount\":\"0.00\",\"out_trade_no\":\"042017070318552200000009\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.00\"}".getBytes());

        boolean verify = signature.verify(Base64Utils.decodeFromString("QJgsgQcvUysu1xwgyDlKHNYFNZSJFkD3m00TUBUG80pGQAZBywlqWjLgWGyQZXAAL+NbrzxHcUy+Y7DEAWLVGGF9HP5/yHE+8Q3Hq2C0M/D5QSD/8Bdp02LXzU8+s5tz/5CB3vOaATtobFXOMGzuRh3IvJsfUM8Lk7FfQD+Wh/x3hRaLiab04sBG6/FlT8H711zFXgqbo1cyFkHJ2HpqCdJf0v6Ca53NRHxB6e7aIMDOgA/ziU7lQHM76NPAf4T734Iitfuk6i8JCeSg0uiqq0KZe3fmn4rw75E64fuN1OCiEUHdneCu74DNvC9+OYQ075rV9dGIWvwtK62IsS5n1A=="));

        System.out.println("verify:"+verify);
    }



}