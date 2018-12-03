/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test;

import org.junit.Test;

/**
 * @author qiusheng.wu
 * @Filename test.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 11:12</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class test {
    @Test
    public void test1(){
        for (int i=0; i<100; i++){
            new Thread(new TestThread(i)).start();
        }
    }
}