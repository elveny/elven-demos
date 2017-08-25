/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiusheng.wu
 * @Filename AtomicIntegerTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/25 15:51</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AtomicIntegerTest {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    @Test
    public void test1(){
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        System.out.println(ctl.get());
    }



}