/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * @author qiusheng.wu
 * @Filename SomeTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 18:57</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SomeTest {
    @Test
    public void testStringUtils() throws Exception {
        boolean flag = StringUtils.pathEquals("222", "222");
        System.out.println(flag);
    }
}