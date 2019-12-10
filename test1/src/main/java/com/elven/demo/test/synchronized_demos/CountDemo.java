/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.synchronized_demos;

/**
 * @author elven
 * @Filename CountDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/10 22:49</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CountDemo {
    public static void main(String[] args) {
        final Count count = new Count();
        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.synMethod();
                    }
                }, "t1");

        // 新建t2, t2会调用“count对象”的nonSynMethod()方法
        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.nonSynMethod();
                    }
                }, "t2");


        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }
}