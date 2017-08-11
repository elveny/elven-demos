/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author qiusheng.wu
 * @Filename DirectMemoryOOM.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/8 11:51</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;
    public static void main(String[]args)throws Exception{
        Field unsafeField=Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe=(Unsafe)unsafeField.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }}
}