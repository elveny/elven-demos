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
        System.out.println(URLDecoder.decode("http%3A%2F%2Fd.alipay.com%2Fi%2Findex.htm%3FiframeSrc%3Dalipays%253A%252F%252Fplatformapi%252Fstartapp%253FappId%253D20000067%2526url%253Dhttps%25253A%25252F%25252Fmapi.alipay.com%25252Fgateway.do%25253F_input_charset%25253Dutf-8%252526access_info%25253D%2525257B%25252522channel%25252522%2525253A%25252522ALIPAYAPP%25252522%2525257D%252526external_sign_no%25253D022017060616482200003002%252526notify_url%25253Dhttp%2525253A%2525252F%2525252Freceptionapi2.msxf.com%2525252Fthirdparty%2525252FalipayAsynNotifyCallback%252526partner%25253D2088121107184830%252526product_code%25253DGENERAL_WITHHOLDING_P%252526return_url%25253Dhttp%2525253A%2525252F%2525252Freceptionapi2.msxf.com%2525252Fthirdparty%2525252FalipaySynCallback%252526scene%25253DINDUSTRY%25257CAPPSTORE%252526service%25253Dalipay.dut.customer.agreement.page.sign%252526sign%25253DmS%2525252BZvhnaccMZhpJcP17ZsONL4ruvzw%2525252Ff4z8zkFTzYbTkdfzMdkJZBG6O6OGY4YXAkbqOnLF5pt2SPrnpK45QhW7PBEy2NLZOen%2525252BrnGz5rpJk%2525252F12RECLojIz%2525252BWfhz8OH6yeSIWrkS3CDWna4hbmDOBk6x90OQp7s%2525252FhJ%2525252BxQmGmI%2525252BM%2525253D%252526sign_type%25253DRSA%0D%0A", "utf-8"));
    }


}