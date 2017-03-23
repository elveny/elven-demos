/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename JsonTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 15:52</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class JsonTest {
    @Test
    public void test1() throws Exception {
        Map<String, String> mapValue = new HashMap<String, String>();
        System.out.println(JSON.toJSONString(mapValue));
        mapValue.put("aaaa", "bbbb");
        mapValue.put("cccc", "dddd");
        mapValue.put("eeee", "ffff");
        System.out.println(JSON.toJSONString(mapValue));
    }

    @Test
    public void test2(){
        Address address = new Address(1L, "test");
        User user = new User(1L, "name", address);

        String json = JSON.toJSONString(user);

        System.out.println(json);

        User user1 = JSON.parseObject(json, User.class);
        System.out.println(user1.getAddress().getAddr());
    }

}