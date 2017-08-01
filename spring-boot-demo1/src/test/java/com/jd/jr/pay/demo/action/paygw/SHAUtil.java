package com.jd.jr.pay.demo.action.paygw;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author qigang.chen
 * @Filename SHAUtil.java
 * @description 证书操作工具
 * @Version 1.0
 * @History <li>Author: qigang.chen</li>
 * <li>Date: 2017/1/19 10:33</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SHAUtil {

    private static String hexString = "0123456789ABCDEF";

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     */
    public static String encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = new byte[0];
        try {
            bt = strSrc.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * 对字符串进行摘要,摘要算法使用SHA-256
     *
     * @param bts 要加密的字符串的byte数组
     * @return 16进制表示的大写字符串 长度一定是8的整数倍
     */
    public static byte[] encrypt(byte[] bts) {
        MessageDigest md = null;
        byte[] result = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bts);
            result = md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return result;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String byte2HexString(byte[] src) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        String bytes = stringBuilder.toString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        String result = (new BASE64Encoder()).encodeBuffer(key);
        if (null != result) {
            result = result.replace("\r\n", "").replace("\n", "");
        }
        return result;
    }

    /**
     * 将元数据进行补位
     * <p/>
     * 补位后 byte[] = 描述有效数据长度(int)的byte[]+原始数据byte[]+补位byte[]
     *
     * @param sourceData 元数据字符串
     * @return 返回3DES加密后的16进制表示的字符串
     */
    public static byte[] encrypt2HexByte(String sourceData) {
        byte[] source = new byte[0];
        byte[] resultByte = null;
        try {
            //元数据
            source = sourceData.getBytes("UTF-8");

            //1.原数据byte长度
            int merchantData = source.length;
            //2.计算补位
            int x = (merchantData + 4) % 8;
            int y = (x == 0) ? 0 : (8 - x);
            //3.将有效数据长度byte[]添加到原始byte数组的头部
            byte[] sizeByte = intToByteArray(merchantData);
            resultByte = new byte[merchantData + 4 + y];

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
//            byte[] desdata = TDESUtil.encrypt(keys, resultByte);
            return resultByte;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将int 转换为 byte 数组
     *
     * @param i
     * @return
     */
    private static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * 解密后, 恢复进行了补位的16进制表示的字符串数据
     *
     * @return
     */
    public static String decrypt4HexStr(byte[] unDesResult) {

        byte[] hexSourceData = new byte[0];
        try {
//            hexSourceData = hex2byte(data.getBytes("UTF-8"));
            //解密
//            byte[] unDesResult = TDESUtil.decrypt(keys, hexSourceData);
            byte[] dataSizeByte = new byte[4];
            for(int i=0;i<4;i++){
                dataSizeByte[i] = unDesResult[i];
            }
            //有效数据长度
            int dsb = byteArrayToInt(dataSizeByte, 0);

            byte[] tempData = new byte[dsb];
            for (int i = 0; i < dsb; i++) {
                tempData[i] = unDesResult[4 + i];
            }

            return hex2bin(toHexString(tempData));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 16进制字符串转BYTE[]
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        b = null;
        return b2;
    }

    /**
     * 转换成16进制表示的小写字符串
     *
     * @param bts 被转换的byte数组
     * @return 16进制表示的字符串
     */
    public static String byte2HexLowerCase(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des.toLowerCase();
    }

    /**
     * 将byte数组 转换为16进制表示的字符串
     *
     * @param ba
     * @return
     */
    private static String toHexString(byte[] ba) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++)
            str.append(String.format("%x", ba[i]));
        return str.toString();
    }
    private static String hex2bin(String hex) {
        String resultStr="";

        String digital = "0123456789abcdef";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        try {
            resultStr= new String(bytes,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 字符编码问题
            resultStr= new String(resultStr);
        }
        return resultStr;
    }


    /**
     * 将byte数组 转换为int
     *
     * @param b
     * @param offset 位游方式
     * @return
     */
    private static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;//往高位游
        }
        return value;
    }

    /**
     * 将元数据进行补位
     * <p/>
     * 补位后 byte[] = 描述有效数据长度(int)的byte[]+原始数据byte[]+补位byte[]
     *
     * @param sourceData 元数据字符串
     * @return 返回3DES加密后的16进制表示的字符串
     */
    public static byte[] encrypt2Hex(String sourceData) {
        byte[] source = new byte[0];
        byte[] resultByte = null;
        try {
            //元数据
            source = sourceData.getBytes("UTF-8");

            //1.原数据byte长度
            int merchantData = source.length;
            //2.计算补位
            int x = (merchantData + 4) % 8;
            int y = (x == 0) ? 0 : (8 - x);
            //3.将有效数据长度byte[]添加到原始byte数组的头部
            byte[] sizeByte = intToByteArray(merchantData);
            resultByte = new byte[merchantData + 4 + y];

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
//            byte[] desdata = TDESUtil.encrypt(keys, resultByte);
            return resultByte;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
