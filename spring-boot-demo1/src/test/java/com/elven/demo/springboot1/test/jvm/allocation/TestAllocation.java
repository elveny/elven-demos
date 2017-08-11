/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.allocation;

/**
 * @author qiusheng.wu
 * @Filename TestAllocation.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/10 17:53</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestAllocation {
    private final static int _1MB = 1024*1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation(){
        byte[]allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];//出现一次Minor GC
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     */
    public static void testAllocation1(){
        byte[]allocation4;
        allocation4=new byte[4*_1MB];//出现一次Minor GC

    }

    public static void main(String[] args) {
        testAllocation1();
    }
}