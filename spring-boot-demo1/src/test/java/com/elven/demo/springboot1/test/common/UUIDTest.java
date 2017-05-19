/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.util.UUID;

/**
 * @author qiusheng.wu
 * @Filename UUIDTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/17 11:41</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class UUIDTest {

    /**
     * UUID格式：8字节-4字节-4字节-4字节-16字节
     */
    @Test
    public void test1(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);

        String uuid1 = UUID.randomUUID().toString();
        System.out.println(uuid1);

        String uuid2 = UUID.randomUUID().toString();
        System.out.println(uuid2);
    }
}