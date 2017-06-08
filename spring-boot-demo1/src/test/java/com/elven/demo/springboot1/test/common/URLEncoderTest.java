/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author qiusheng.wu
 * @Filename URLEncoderTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/2 16:14</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class URLEncoderTest {
    @Test
    public void encodeTest1() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("111", "utf-8"));
    }

    @Test
    public void decodeTest1() throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("%3Chtml%3E%3Chead%3E%3Cmeta+http-equiv%3D%22Content-Type%22+content%3D%22text%2Fhtml%3B+charset%3Dutf-8%22%2F%3E%3C%2Fhead%3E%3Cbody%3E%0D%0A%3Cform+id%3D%22alipaysubmit%22+name%3D%22qform%22+action%3D%22https%3A%2F%2Fmapi.alipay.com%2Fgateway.do%3F_input_charset%3Dutf-8%26access_info%3D%257B%2522channel%2522%253A%2522ALIPAYAPP%2522%257D%26external_sign_no%3D022017060821102300022000%26notify_url%3Dhttp%253A%252F%252F10.16.64.130%253A8082%252Foutter%252Falipay%252FwapSignAsyNotify%26partner%3D2088521100911233%26product_code%3DGENERAL_WITHHOLDING_P%26return_url%3Dhttp%253A%252F%252F10.16.64.130%253A8082%252Foutter%252Falipay%252FwapSignSynNotify%26scene%3DINDUSTRY%7CAPPSTORE%26service%3Dalipay.dut.customer.agreement.page.sign%26sign%3DFl86c1sVMEhAKq1x8d9cUoKQJLzhBhEtNt4KnKXUBDkOClE%252Beapum5pizlUzdfhC2VkEClgdo5Un9fA3NH8rH0nZz8k%252BOE%252BtsU4%252F09Q5wFbq16BpzjkzNNomO8IpsuwRig11bT3%252FNBYv66oqrFcsd80wvwWTqJU8yQrASBZNRX0%253D%26sign_type%3DRSA%22+method%3D%22post%22%3E%0D%0A%3C%2Fform%3E%3Cscript%3Edocument.forms%5B%27qform%27%5D.submit%28%29%3B%0D%0A%3C%2Fscript%3E%0D%0A%3C%2Fbody%3E%3C%2Fhtml%3E%0D%0A", "utf-8"));
    }


}