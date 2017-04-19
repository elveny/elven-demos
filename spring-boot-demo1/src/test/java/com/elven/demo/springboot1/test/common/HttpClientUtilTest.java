/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import com.elven.demo.springboot1.common.util.HttpClientTool;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename HttpClientUtilTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/19 17:40</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class HttpClientUtilTest {

    @Test
    public void get() throws IOException {
        new HttpClientTool().get("http://mock3.msxf.lotest/dac/SinCutQueryServletGBK?orderNo=0000000000003008");
    }

    @Test
    public void post() throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderNo", "0000000000003008");
        new HttpClientTool().post("http://mock3.msxf.lotest/dac/SinCutQueryServletGBK", params);
    }
}