/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.lambda;

/**
 * @author qiusheng.wu
 * @Filename RunnableDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/2 16:54</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RunnableDemo {
    public static void main(String[] args) {
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }
}