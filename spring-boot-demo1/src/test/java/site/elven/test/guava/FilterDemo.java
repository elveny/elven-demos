/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename FilterDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:35</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class FilterDemo {
    public static void main(String[] args) {
        List<String> list= Lists.newArrayList("moon","dad","refer","son");
        Collection<String> palindromeList= Collections2.filter(list, input -> {
            return new StringBuilder(input).reverse().toString().equals(input); //找回文串
        });
        System.out.println(palindromeList);
    }
}