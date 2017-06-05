/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiusheng.wu
 * @Filename HashMapTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/5 16:48</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class HashMapTest {

    @Test
    public void test1(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xxx", "xxx");
        map.put(null, "yyy");
        System.out.println(map);
        System.out.println(map.get(null));
    }

    @Test
    public void test2(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("xxx", "xxx");
//        map.put(null, "yyy");
        System.out.println(map);
//        System.out.println(map.get(null));
    }
}