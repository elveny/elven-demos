/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.elven.demo.test.synchronized_demos;

/**
 * @author elven
 * @Filename Count.java
 * @description
 * @Version 1.0
 * @History <li>Author: elven</li>
 * <li>Date: 2019/10/10 22:47</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AnotherCount {
    // 含有synchronized同步块的方法
    public void synMethod1() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " synMethod1 loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }

    // 非同步的方法
    public void synMethod2() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " synMethod2 loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }

    }
}