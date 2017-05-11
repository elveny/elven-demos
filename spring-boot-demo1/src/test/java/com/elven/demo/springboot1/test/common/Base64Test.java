/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;
import org.springframework.util.Base64Utils;

/**
 * @author qiusheng.wu
 * @Filename Base64Test.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/11 15:22</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class Base64Test {

    @Test
    public void encode(){

        String enStr = Base64Utils.encodeToString("M".getBytes());

        System.out.println(enStr);
    }
}