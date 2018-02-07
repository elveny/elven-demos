/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author qiusheng.wu
 * @Filename BitMapDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:28</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class BitMapDemo {
    public static void main(String[] args) {
        BiMap<String,String> biMap= HashBiMap.create();
        biMap.put("sina","sina.com");
        biMap.put("qq","qq.com");
        biMap.put("sina","sina.cn"); //会覆盖原来的value
       /*
         在BiMap中,如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常
         如果对特定值,你想要强制替换它的键，请使用 BiMap.forcePut(key, value)
        */
//        biMap.put("tecent","qq.com"); //抛出异常
        biMap.forcePut("tecent","qq.com"); //强制替换key
        System.out.println(biMap);
        System.out.println(biMap.inverse().get("sina.com")); //通过value找key
        System.out.println(biMap.inverse().get("sina.cn")); //通过value找key
        System.out.println(biMap.inverse().inverse()==biMap); //true

    }
}