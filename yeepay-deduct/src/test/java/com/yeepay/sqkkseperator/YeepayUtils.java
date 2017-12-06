/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.yeepay.sqkkseperator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename YeepayUtils.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/11/22 21:25</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class YeepayUtils {

    public static String buildPlainText(Map<String, String> headers, Map<String, Object> params,String apiUri) throws UnsupportedEncodingException {

        String canonicalURI = "/"+apiUri.replace(":", URLEncoder.encode(":", "UTF-8"));

        String canonicalQueryString = MapUtils.createLinkString(params);

        String appKey = headers.get("x-yop-appkey");
        String timestamp = headers.get("x-yop-date");
        String requestId = headers.get("x-yop-request-id");
        String authString = "yop-auth-v2/" + appKey + "/" + timestamp + "/" + "1800";

        String canonicalRequest = new StringBuilder("")
                                    .append(authString).append("\n")
                                    .append("POST").append("\n")
                                    .append(canonicalURI).append("\n")
                                    .append(canonicalQueryString).append("\n")
                                    .append("x-yop-appkey").append(":").append(appKey).append("\n")
                                    .append("x-yop-date").append(":").append(timestamp).append("\n")
                                    .append("x-yop-request-id").append(":").append(requestId)
                                    .toString()
                ;

        return canonicalRequest;
    }

}