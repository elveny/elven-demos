/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.yeepay.sqkkseperator;

import com.yeepay.shade.org.springframework.util.ObjectUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename AESCryptTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/11/23 14:06</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AESCryptTest {

    String yop_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6p0XWjscY+gsyqKRhw9MeLsEmhFdBRhT2emOck/F1Omw38ZWhJxh9kDfs5HzFJMrVozgU+SJFDONxs8UB0wMILKRmqfLcfClG9MyCNuJkkfm0HFQv1hRGdOvZPXj3Bckuwa7FrEXBRYUhK7vJ40afumspthmse6bs6mZxNn/mALZ2X07uznOrrc2rk41Y2HftduxZw6T4EmtWuN2x4CZ8gwSyPAW5ZzZJLQ6tZDojBK4GZTAGhnn3bg5bBsBlw2+FLkCQBuDsJVsFPiGh/b6K/+zGTvWyUcu+LUj2MejYQELDO3i2vQXVDk7lVi2/TcUYefvIcssnzsfCfjaorxsuwIDAQAB";
    String isv_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCxvbcO80BpbiirZUOtTOUfTQroEpbT6cSpG1EJ+wfjzc/N85FiMfIpnUFPdsZOT+tiMljVdV8JWjz/QtvhxWY4wwkReymXzxaIusxfslWH7h32qnJ0UP7TgjvDRmewKJrOSmHUS1ImWp4qb1X6DU4wxdP7c/OYaqkljBpJpVqJ4UU9DcDCzsyWwdq8i7IJmMbP6OYt8KGEfeQdkbdxUqNZnxvi25uCRgniUpcApfXFptONNCF/1/7qU3X0Vb4RG6KleZo9YnqP2YSwrRm9TgZ7MfxjDKyZbTP9Pe31bwNf7gfLLgUu4r4OGyNssR1aD648HfM3rDjclJepC1AiT+n7AgMBAAECggEBAIra+mr8kGKNQ8p6pv95Zjoo1w9sjlZpd1DhXVMdwREv1VtBIGAFQvlNuBsbYFsHxo0FZi8ErcVBsQt+MQdVTsGjZK86d8j4aNNSk51jVbyGwvPUPuwt5pZYVNX+Z7zQS/hDVeAjI7+A5bWjGxjpUh96PVxhPnnhQdlqdZL5Uh0KCP7BmFQ9NSW6EotSrbWVDbWW6Bzke9xnEYa7h2QkdwrzitF+HjkuwQsAgAAsN2bMzkn65fpYTI9LH18KBID1763WBlKC5UYB7FtyAr/xEZM6IW1V2S5VPQjmsQLqDKUQAvpJYtwQIUqrFSCYaluB1FwRncrgiQFp3mKYmClxGJECgYEA5XDlR5nuoOr+lMVWSYi+hRbUGgoxWmx2EBHchTRdLmDIW5CwuKOtGD3tq84OXjmZq2q28j2rZdOICeYwuYysGQkVoSPs6KqjoX8hDeBwYYOlni0zvvzajZ2Zs1Ws620X23u+/rodMXv403PrL1PSMa1z8XMQ3T3G6TpvGwXzzJMCgYEAxlDHGdf4rdvbjWuKL0kxUmzSWP0HR8T6tBIz4MsNZDbxMWNUA3BLnwv5OLxb18sSU5EPauxmsdJR4uqxJIXnTTsZoDR9GyskELez7RenIXkSgynqb5kmk3FJyaTRZqtoLP0ioMRUCkTwzrSy+YKOgkC+IMQhVgpcdc27beWOtfkCgYEAgrqBmPtnEL70URXjJUDJtREdxrx6BFh6KIvUxvzf/tXcMvfNCVcRABgA/HwKibtuCFJkbL+gkgMlpuZGauJxCE65yTer+6GGXDUQQ1TXE2isC+Ubb72oVTov4hN11CozhrYKTB8FFTXav7hzj2LGB9IZlsAGItjZJfhKZ+5LTAsCgYB3NzYJVih7M3CvehdOx7wrpZlpv2nx/fsL5uli2A3L0a96lhB6JLaA/Oyr66d2ePAiZlCTYVt2yE1LkPQ+VXSvm7iS8xrGC1AZ8KTsAU0KNUMosDjrL3DeL7tAyaDMDHyKlv2LEZrHtZvhVwVEMvTCXnMtNVoo4/+jHbLvpJD+2QKBgQCvoKXxCapsov1gpBpYANIUsdeoBqvhn/RwGHZj+7CMJPr6CDv2dGm6mh4F11tiwmwOO2wVwaO65LR4KOPiRoYOL+nGBAuPAQqz3jhfAAcBMKGXKIq+XWskb/ZrZgvc4hYUyt3WoAWoK1zfao3C9MA3lNh+ojpN6B0qmPMOwjSQnw==";

    @Test
    public void decryptTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        String response="NlylsGELAg2o8X2NqD9qf7kZdiiewcyUZ1PYQnxOFMVMtdmOyI0p2eY6agNSxhXu0sqxaCkoVS2vkBsF_mDQOLryIoT4D9tV3SwElT1uwB7z8vb4HpDewjVY10R1fG_LWjTZ7zU1pdX_q0uAlJOQfeARQmnt_72yGBHVAeBpKIi0Q7F6fgzZCM8HmLi2JJQkqxoEr5ZkP5EDR9DxJH1CKca9p0ezcM34mRVc7S_8XCNRrEGkCq1dyUwdrgrCNhyrVSTFKOAkjvO8xwWDhgwgnHzq9oP0xI8jVx-tcDtYRmxtxhOIwWhqn4LGWXYsAT4Fm61PPTgXVIivye0lbhliZA$8LVaWiT2RB660xY2EIxDttoH0IVYHuHSAnZ_VdVVk3iFZTCmSfjCOWn0NlON6pBNUgXizlpRtjU1sTWSyP7HRXK5uSe57u2u7diaGr-C-VBlitGM7ooxu_8WqrmRVWI9s1h9Dz-IEV-l5s9H1A-Mre6iolgORDlusqlTAr_Bn92fME5oqyRkyQs14b4poyzeXGWTYRkJLhmS-rvKQanqPHkzWqcwMU_gEMSCljjUjMTwB-S5wh5NboGVOoidnB3pSQpU2NyfPKG7Lv5jd6_1hcOx581Cv0XJg5lCgKNfLyho7HNKQCEhW38s9IqEjnjP4d2tpWMueueLU3TXONKh4YZsx2_MrA0g0uzrZjwLjSmgTAFVAULNVTMnCQnfLQtoLx-OYgaLTumEr2A9qtYgC5mm0-JobRn0tNeU6aTOvvqtHiUqHxVx6DIDPxFBVNspiwi4-4naiQiNj2H5w__AC5LYWqtnLr_uNFYqHhDvl9srUnCBl23OVFWfl0uPX2QOwi4ckHUy7hPTsXXNUngqO5FqTjpIzcslKfkBuZwH8Ulgcg_Vw1rADtvQKrFIHLw2GVxUYfCLpt82U5ymT6zgCbOVIf66nrUZxkwotUnh1V0hPU2FoTWe-HS6hstF2gpY-BY-TbZuervpFzmp2IKnISrbgUSbYDkaWFsfO2yc4wt7v7DnQppeHr_3lYZEv4-q6bbijX0WzvLDRlNqA1dqnRbOLTdo9gg4fhhWXiElpMua08eIcbvh5-yCB3wlZJm68SuwlkqY7KyV5TElr60JNg$AES$SHA256";

        String[] args = response.split("\\$");

        String encryptedRandomKeyToBase64 = args[0];
        String encryptedDataToBase64 = args[1];
        byte[] randomKey = decryptOfRSA(Base64.decodeBase64(encryptedRandomKeyToBase64));

        byte[] encryptedData = decrypt(Base64.decodeBase64(encryptedDataToBase64), randomKey);
        String data = new String(encryptedData);

        System.out.println(data);

    }

    @Test
    public void encryptTest1() throws Exception {

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(isv_private_key.getBytes("UTF-8")));
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
//            PrivateKey priKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
        System.out.println("privateKey: "+priKey);
//////////////////////////////////////////////////////////////////////////////
//            keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priKey);

        byte[] encryptData = doFinal(cipher , "我有一头小毛驴从来也不骑".getBytes() , 117);
        System.out.println(Base64Utils.encodeToString(encryptData));

        byte[] encryptData1 = doFinal1(cipher , "我有一头小毛驴从来也不骑".getBytes() , 117);
        System.out.println(Base64Utils.encodeToString(encryptData1));

    }

    @Test
    public void decryptTest1() throws Exception {

        String base64DecryptData = "fLXky8zEkuSEWQS/JCHgdBUFTGd5M7mX2H1Mv5xS+hnbthnghaif6BAs09sFYgsIXSuue19InM2xUheAzt+0TqlKIbEoK+9pRMkao+mPjWNrU5mU9yd5kg8UU1WdHbCtanOup03QSnPsCgWUI8TSJ9h+NXG/mJ2Fv2Hjb7tsUTLYkjpfAaW4uVFsSy+eBNOvV8r2achFVI0bJpPN30VcSQfxCVZSV7esl119W+tFF4j0P2e/CB9+kjCUoDaq7wbo6K9few9SPj0aJaPnvh8f08eq7V0is0cjo0GdEkUgIRKxsHwnBVbdp3vMMDIRQKn5uhDOW4bHJcbYxkS1VwYqiQ==";

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(yop_public_key.getBytes("UTF-8")));
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
//            PrivateKey priKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
        System.out.println("privateKey: "+priKey);
//////////////////////////////////////////////////////////////////////////////
//            keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);

//        byte[] decryptData = doFinal(cipher , Base64Utils.decodeFromString(base64DecryptData) , 128);
//        System.out.println(Base64Utils.encodeToString(decryptData));

        byte[] decryptData1 = doFinal1(cipher , Base64Utils.decodeFromString(base64DecryptData) , 128);
        System.out.println(Base64Utils.encodeToString(decryptData1));
    }

    public byte[] decrypt(byte[] cipherText, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            byte[] enCodeFormat = secretKey.getEncoded();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, new SecretKeySpec(enCodeFormat, "AES"));
            return cipher.doFinal(cipherText);
        } catch (Exception var6) {
            throw new RuntimeException("decrypt fail!", var6);
        }
    }

    public byte[] decryptOfRSA(byte[] encryptedData){

        try {
            KeyFactory keyFactory = null;

//////////////////////////////////////////////////////////////////////////////
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(isv_private_key.getBytes("UTF-8")));
        // KEY_ALGORITHM 指定的加密算法
            keyFactory = KeyFactory.getInstance("RSA");
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
//            PrivateKey priKey = RSAKeyUtils.string2PrivateKey(isv_private_key);
            System.out.println("privateKey: "+priKey);
//////////////////////////////////////////////////////////////////////////////
//            keyFactory = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
//            return cipher.doFinal(encryptedData);
            return doFinal1(cipher , encryptedData , 128);
//            return multiples(cipher , encryptedData , 128);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte [] doFinal(Cipher cipher ,byte[] data  , int maxBlock) throws Exception{
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

    public byte [] doFinal1(Cipher cipher ,byte[] data  , int maxBlock) throws Exception{
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > maxBlock) {
                cache = cipher.doFinal(data, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxBlock;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
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

}