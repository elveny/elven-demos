/**
 * msxf.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.yeepay.sqkkseperator;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author lei.xiong02
 * @Filename StringUtils.java
 * @description
 * @Version 1.0
 * @History <li>Author: lei.xiong02</li>
 * <li>Date: 2016/12/21 18:39</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * 根据编码转换字节数组为字符串
     * @param bytes
     * @param charset
     * @return  不支持的编码返回null
     */
    public static String newStr(final byte[] bytes,String charset){
         try {
            return bytes == null ? null : new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
             return null;
        }
    }

    /**
     * @Description 对象转String
     * @Params
     * @Return
     * @Exceptions
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }


    /**
     * @Description 判断空
     * @Params
     * @Return
     * @Exceptions
     */
    public static boolean isEmpty(Object obj) {
        return obj == null || valueOf(obj).length() == 0;
    }

    /**
     * @Description 是否为空白
     * @Params
     * @Return true: 是   false:不是
     * @Exceptions
     */
    public static boolean isBlank(Object obj) {
        return isBlank(valueOf(obj));
    }

    /**
     * @Description 是否不是空白
     * @Params
     * @Return true: 不是   false:是
     * @Exceptions
     */
    public static boolean isNotBlank(Object obj) {
        return isNotBlank(valueOf(obj));
    }

    /**
     * @Description 将首字母转换为小写
     * @Params
     * @Return
     * @Exceptions
     */
    public static String convertFirstCharToLower(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }


    /***
     * 拼接字符串,如果包含空值(null或"")则已"null"拼接
     * */
    public static String join(String... args) {
        StringBuffer sb = new StringBuffer();
        for (String s : args) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * @Description 替换回车换行
     * @Params
     * @Return
     * @Exceptions
     */
    public static String replaceSpace(String str) {
        if (null != str) {
            return str.replace("\r\n", "").replace("\n", "");
        }
        return str;
    }

    /**
     * @Description 替换所有的回车换行
     * @Params
     * @Return
     * @Exceptions
     */
    public static String replaceAllSpace(String str) {
        if (null != str) {
            return str.replaceAll("\r\n", "").replaceAll("\n", "").replaceAll("\r", "");
        }
        return "";
    }

    /**
     * @Description 得到数组第一个值
     * @Params
     * @Return
     * @Exceptions
     */
    public static String getFirstByArray(String[] strArray) {
        if (null != strArray && strArray.length > 0) {
            return strArray[0];
        }
        return "";
    }

    /**
     * 中文转unicode编码,非中文不转
     *
     * @param zhStr
     * @return
     */
    public static String toUnicode(String zhStr) {
        return StringEscapeUtils.escapeJava(zhStr);
    }

    /**
     * unicode转中文
     *
     * @param unicode
     * @return
     */
    public static String unicodeTo(String unicode) {
        if (StringUtils.isBlank(unicode)) return "";
        String[] arr = unicode.split("\\\\u", -1);
        StringBuffer sb = new StringBuffer();
        if (arr != null && arr.length > 0) {
            for (String str : arr) {
                if (str.length() == 4) {
                    sb.append((char) Integer.parseInt(str, 16));
                }
            }
        }

        return sb.toString();
    }

    /**
     * 获取后几位字符
     *
     * @param str
     * @param num
     * @return
     */
    public static String getLastStrWithNum(String str, int num) {
        return StringUtils.substring(str, str.length() - num);
    }

    /**
     * 去除回车换行符
     *
     * @param str
     * @return
     */
    public static String trimLine(String str) {
        if (str == null) {
            return null;
        }
        if (!str.endsWith("\n") && !str.endsWith("\r")) {
            return str;
        }
        return trimLine(str.substring(0, str.length() - 1));
    }

    /**
     * 空转默认值
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String nullToDefault(String obj, String defaultValue) {
        if (StringUtils.isBlank(obj)) {
            return defaultValue;
        }
        return obj;
    }

    /**
     * 生成指定长度随机数，最大支持18位长
     *
     * @param len 需要的随机数长度，最大18
     * @return
     */
    public static String random(int len) {
        Random rm = new Random();
        double t = rm.nextDouble();
        Double pross = (1 + t) * Math.pow(10, len);
        String fixstr = String.valueOf(pross.longValue());

        if (null != fixstr) {
            int strlen = fixstr.length();
            if (fixstr.length() > len) {
                fixstr = fixstr.substring(strlen - len, fixstr.length());
            } else {
                for (int i = 0; i < len - strlen; i++) {
                    fixstr = "0" + fixstr;
                }
            }
        }
        return fixstr;
    }

    /**
     * trim null->"null"
     *
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        return String.valueOf(org.apache.commons.lang.StringUtils.trimToNull(str));
    }

    /**
     * 报文长度左补内容
     *
     * @param lengthStr   需要补充的字符串
     * @param totalLength 补充后字符串的长度
     * @param padding     需要补充的内容
     * @return
     */
    public static String appendLeftStr(String lengthStr, int totalLength, String padding) {
        if (null == lengthStr) {
            lengthStr = "";
        }
        int len = lengthStr.length();
        if (len >= totalLength) {
            return lengthStr;
        }
        int ss = totalLength - len;
        String res = "";
        for (int i = 0; i < ss; i++) {
            res = res + padding;
        }
        return res + lengthStr;
    }

    /**
     * 报文长度右补内容
     *
     * @param lengthStr   需要补充的字符串
     * @param totalLength 补充后字符串的长度
     * @param padding     需要补充的内容
     * @return
     */
    public static String appendRightStr(String lengthStr, int totalLength, String padding) {
        if (null == lengthStr) {
            lengthStr = "";
        }
        int len = 0;
        try {
            len = lengthStr.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (len >= totalLength) {
            return lengthStr;
        }
        int ss = totalLength - len;
        String res = "";
        for (int i = 0; i < ss; i++) {
            res = res + padding;
        }
        return lengthStr + res;
    }
}