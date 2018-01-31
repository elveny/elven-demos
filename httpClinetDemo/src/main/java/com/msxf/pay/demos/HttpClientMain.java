/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.msxf.pay.demos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename HttpClientMain.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/16 11:43</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class HttpClientMain {

    public static void main(String[] args) throws IOException {
        new HttpClientMain().get();
        new HttpClientMain().post();

    }

    public void get() throws IOException {
        /**
         * 生成地址为：https://paygw.msxf.com/outter/abc/batchDeductAsyNotify
         */
        new HttpClientTool().get("http://localhost:8092/outter/abc/batchDeductAsyNotify?orderNo=0000000000003008&replyFileName=0000000000003008.rpy");
    }

    public void post() throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderNo", "0000000000003008");
        params.put("replyFileName", "0000000000003008.rpy");
        /**
         * 生成地址为：https://paygw.msxf.com/outter/abc/batchDeductAsyNotify
         */
        new HttpClientTool().post("http://localhost:8092/outter/abc/batchDeductAsyNotify", params);
    }

    public void post1() throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderNo", "0000000000003008");
        params.put("replyFileName", "0000000000003008.rpy");
        /**
         * 生成地址为：https://paygw.msxf.com/outter/abc/batchDeductAsyNotify
         */
        new HttpClientTool().post("http://localhost:8092/outter/abc/batchDeductAsyNotify", params);
    }
}