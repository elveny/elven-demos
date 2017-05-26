/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename ArraysTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/25 18:26</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ArraysTest {

    @Test
    public void test1(){
        String[] arrys = {"11", "22", "33"};
        List<String> queryTransCodeList = null;
        queryTransCodeList = Arrays.asList(arrys);
        System.out.println(queryTransCodeList);
    }

    @Test
    public void test2(){
        String[] arrys = {"11", "22", "33"};
        System.out.println(CollectionUtils.arrayToList(arrys));
        System.out.println(CollectionUtils.arrayToList(null));
        System.out.println(CollectionUtils.isEmpty(CollectionUtils.arrayToList(null)));
    }
}