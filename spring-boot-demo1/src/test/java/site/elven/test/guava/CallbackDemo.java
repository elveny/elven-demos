/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author qiusheng.wu
 * @Filename CallbackDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 20:58</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CallbackDemo {
    public static void main(String[] args) {
        Cache<String,String> cache= CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
        try {
            String result=cache.get("java", () -> "hello java");
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}