/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.org.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

/**
 * @author qiusheng.wu
 * @Filename StringUtilsTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/17 13:34</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class StringUtilsTester {

    @Test
    public void join(){
        String ss = StringUtils.join(1,2,3);
        System.out.println(ss);
    }

    @Test
    public void contains(){
        System.out.println(StringUtils.contains("111222", "1111"));
        System.out.println(StringUtils.containsAny("111222", "1111"));
    }

    @Test
    public void substring(){
        System.out.println(org.apache.commons.lang.StringUtils.substring("12345678", 0, 5));
    }

    @Test
    public void test1(){
        String ss = "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;Root&gt;&lt;Header&gt;&lt;outTradeNo&gt;201712250000000000017039&lt;/outTradeNo&gt;&lt;Response&gt;&lt;code&gt;0001&lt;/code&gt;&lt;message&gt;&#x4EA4;&#x6613;&#x5931;&#x8D25;&lt;/message&gt;&lt;/Response&gt;&lt;/Header&gt;&lt;Result&gt;&lt;resultCode&gt;200013&lt;/resultCode&gt;&lt;resultDesc&gt;&#x67E5;&#x8BE2;&#x7ED3;&#x679C;&#x4E3A;&#x7A7A;&lt;/resultDesc&gt;&lt;accDate&gt;20171225&lt;/accDate&gt;&lt;/Result&gt;&lt;Mac&gt;&lt;signType&gt;01&lt;/signType&gt;&lt;sign&gt;33010A8194A302634326CBE84940640E&lt;/sign&gt;&lt;/Mac&gt;&lt;/Root&gt;";
        System.out.println(HtmlUtils.htmlUnescape(ss));
    }
}