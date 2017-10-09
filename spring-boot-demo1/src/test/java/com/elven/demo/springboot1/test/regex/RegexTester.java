/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.regex;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
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

    @Test
    public void test8(){
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").toString());
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").flags());
        System.out.println(Pattern.compile("\\b\\w+(?=ing\\b)").pattern());
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").split("ss")[0]);
        System.out.println(Arrays.asList(Pattern.compile("\\b\\w+(?=ing\\b)").split("I'm singing while you're dancing")));
        System.out.println(Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b").matcher("go"));
        System.out.println(Pattern.compile("\\b\\w+(?=ing\\b)").matcher("I'm singing while you're dancing").find());
        System.out.println(Pattern.compile("\\b\\w+(?=ing\\b)").matcher("I'm singing while you're dancing").matches());

    }

    /**
     * 匹配验证-验证Email是否正确
     */
    @Test
    public void test9(){
        // 要验证的字符串
        String str = "service@xsoftlab.csssssssssssssssssssssssssssssssssssssssssssssss";
        // 邮箱验证规则
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    /**
     * 在字符串中查询字符或者字符串
     */
    @Test
    public void test10(){
        // 要验证的字符串
        String str = "baike.xsoftlab.net";
        // 正则表达式规则
        String regEx = "baike.*";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean rs = matcher.find();
        System.out.println(rs);
    }

    /**
     * 账号验证
     */
    @Test
    public void test11(){
        // 要验证的字符串
        String str = "aaaa-_.";
        // 邮箱验证规则
//        String regEx = "[a-zA-Z0-9]\\w{1,32}";
        String regEx = "[\\w\\-\\.]{1,32}|(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    @Test
    public void test12(){
        // 要验证的字符串
        String str = "140117071400010720";
        // 邮箱验证规则
        String regEx = "[\\w\\-\\.]{1,64}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    @Test
    public void test13(){
        // 要验证的字符串
        String str = "DESede/ECB/NoPadding";
        // 邮箱验证规则
        // 编译正则表达式
        Pattern pattern = Pattern.compile("\\w+/\\w+/\\w+");

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.find();
        System.out.println(rs);
    }

    @Test
    public void test14(){
        // 要验证的字符串
        String str = "宝贝格子（北京）科技(有限)公司二";
        // 邮箱验证规则
        // 编译正则表达式
//        Pattern pattern = Pattern.compile("^[\\w\\-\\.\\u00b7\\u2022\\u4E00-\\u9FA5]{1,64}$");
        // - . · • （ ） ( ) 中文（从一到龥）
        Pattern pattern = Pattern.compile("^[\\w\\-\\.\\u00b7\\u2022\\uff08\\uff09\\u0028\\u0029\\u4E00-\\u9FA5]{1,64}$");

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.find();
        System.out.println(rs);
    }
}