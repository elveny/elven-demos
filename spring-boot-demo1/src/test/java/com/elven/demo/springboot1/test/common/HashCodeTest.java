/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

/**
 * @author qiusheng.wu
 * @Filename HashCodeTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/19 9:49</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class HashCodeTest {

    @Test
    public void test1(){
        String str1 = "Aa";
        String str2 = "BB";
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());

        char c = 'a';
        System.out.println(c);
        System.out.println((int)c);
    }
}