/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

/**
 * @author qiusheng.wu
 * @Filename StringEqualsTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/9 14:48</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class StringEqualsTest {

    @Test
    public void test1(){
        String s1 = "Hello";
        String s2 = new String("Hello");
        String s3 = "He" + "llo";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }
}