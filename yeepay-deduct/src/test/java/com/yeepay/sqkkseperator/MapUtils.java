/**
 * msxf.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.yeepay.sqkkseperator;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author yong.zhang
 * @Filename MapUtils.java
 * @description MAP处理工具类
 * @Version 1.0
 * @History <li>Author: yong.zhang</li>
 * <li>Date: 2016/12/26 13:54</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MapUtils {


    /**
     * 将map的kv按规则拼接为字符串(自然顺序，过滤空值)
     *
     * @param params
     * @return
     */
    public static String createLinkString(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            if (StringUtils.isNotBlank(value)) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1).toString();
        }
        return sb.toString();
    }

    /**
     * 将map的kv按规则拼接为字符串(自然顺序，不过滤空值)
     *
     * @param params
     * @return
     */
    public static String createLinkStringIncludeNulls(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            if (StringUtils.isNotBlank(value)) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            } else {
                sb.append(key).append("=").append("").append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1).toString();
        }
        return sb.toString();
    }

    public static String createLinkString(Map<String, Object> params, String charset) {
        try{
            List<String> keys = new ArrayList<String>(params.keySet());
            Collections.sort(keys);
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                Object value = params.get(key);
                if (StringUtils.isNotBlank(value)) {
                    sb.append(key).append("=").append(URLDecoder.decode((String) params.get(key), charset)).append("&");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1).toString();
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException e){

            return null;
        }

    }

    /**
     * map转换为json串
     *
     * @param map
     * @return
     */
    public static String toJsonStr(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }


    /**
     * 1=v1&k2=v2转换为map
     *
     * @param kvStr
     * @return
     */
    public static Map<String, Object> kvStr2MapWithForm(String kvStr) {
        Map<String, Object> kvMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(kvStr)) {
            return kvMap;
        }
        String[] pairs = kvStr.trim().split("&");
        if (pairs == null) {
            return kvMap;
        }
        for (String pair : pairs) {
            if (StringUtils.isBlank(pair)) {
                continue;
            }
            int index = pair.indexOf("=");
            if (-1 == index) {
                continue;
            }
            kvMap.put(pair.substring(0, index).trim(), pair.substring(index + 1).trim());
        }
        return kvMap;
    }




}
