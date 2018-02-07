/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename ListDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/2 17:19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ListDemo {
    public static void main(String[] args) {
        new ListDemo().test1();
        new ListDemo().test2();
        new ListDemo().test3();
    }

    public void test1(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }
    }

    public void test2(){
        // Java 8之后：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
    }

    public void test3(){
        // Java 8之后：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }
}