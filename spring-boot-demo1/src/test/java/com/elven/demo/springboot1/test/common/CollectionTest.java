/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename CollectionTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/25 10:13</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CollectionTest {

    @Test
    public void mapTest(){

        Map<String, Object> map = new HashMap<String, Object>();

        for (int i = 0; i < 10; i++) {
            map.put("k_"+i, "v_"+(i*8));
        }

        System.out.println(map);

        for (int i = 0; i < 10; i++) {
            System.out.println(map.get("k_"+i));
            if(i % 2 == 0){
                map.remove("k_"+i);
            }
        }
        System.out.println(map);

        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, Object> item = it.next();

            if(item.getKey().equals("k_5")){
//                map.remove(item.getKey());    // 会导致下一个it.next()抛出异常：java.util.ConcurrentModificationException
                it.remove();
            }
        }

        System.out.println(map);

    }
}