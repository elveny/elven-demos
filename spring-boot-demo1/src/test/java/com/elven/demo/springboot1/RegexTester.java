/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author qiusheng.wu
 * @Filename RegexTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/7 15:12</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RegexTester {

    @Test
    public void test1(){
        System.out.println(Pattern.compile("^0\\d{2}-\\d{8}$").matcher("023-99387767").matches());
    }

    @Test
    public void test2(){
        System.out.println(Pattern.compile("deerchao\\.net").matcher("deerchao.net").matches());
    }

    @Test
    public void test3(){
        System.out.println(Pattern.compile("[aeiou]").matcher("a").matches());
        System.out.println(Pattern.compile("[aeiou]").matcher("q").matches());
    }

    /**
     * 电话号码匹配
     */
    @Test
    public void test4(){
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("(010)88886666").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("010)88886666").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("(01088886666").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("022-22334455").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("02912345678").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("029 12345678").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}[) -]?\\d{8}").matcher("0816 12345678").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2,3}[) -]?\\d{8}").matcher("xxx 0816 12345678").matches());
        System.out.println(Pattern.compile("^\\(?0\\d{2,3}[) -]?\\d{8}$").matcher("0816 12345678").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}\\)?[- ]?\\d{8}|0\\d{2}[- ]?\\d{8}").matcher("081 12345678").matches());
        System.out.println(Pattern.compile("\\(?0\\d{2}\\)?[- ]?\\d{8}|0\\d{2}[- ]?\\d{8}").matcher("(081 12345678").matches());
    }

    @Test
    public void test5(){
        System.out.println(Pattern.compile("\\d{2,5}").matcher("8888").matches());
    }

    /**
     * \S+匹配不包含空白符的字符串。
     */
    @Test
    public void test6(){
        System.out.println(Pattern.compile("\\S+").matcher("8888").matches());
        System.out.println(Pattern.compile("\\S+").matcher("8 888").matches());
        System.out.println(Pattern.compile("\\S+").matcher(" 8888").matches());
    }

    /**
     * 后向引用。
     */
    @Test
    public void test7(){
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").matcher("go").matches());
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").matcher("go hi").matches());
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").matcher("go go").matches());
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").matcher("go go go").matches());
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b\\s+\\1\\b").matcher("go go go").matches());
    }
}