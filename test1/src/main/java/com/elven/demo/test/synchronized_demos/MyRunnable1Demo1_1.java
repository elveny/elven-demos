/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.synchronized_demos;

/**
 * @author elven
 * @Filename MyRunnable1Demo1_1.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/10 22:42</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MyRunnable1Demo1_1 {
    public static void main(String[] args) {
        Runnable demo = new MyRunable1();     // 新建“Runnable对象”

        Thread t1 = new Thread(demo, "t1");  // 新建“线程t1”, t1是基于demo这个Runnable对象
        Thread t2 = new Thread(demo, "t2");  // 新建“线程t2”, t2是基于demo这个Runnable对象
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }
}