/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

/**
 * @author qiusheng.wu
 * @Filename JavaVMStackSOF.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/7 20:46</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLength(){
        stackLength++;
        stackLength();
    }

    /**
     * 无限递归调用方法，导致方法达到最大栈深度，抛出异常。
     * -Xss128k
     * 抛出异常：java.lang.StackOverflowError
     * @param args
     */
    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLength();
        }
        catch (Throwable e){
            System.out.println("stack length:"+oom.stackLength);
            throw e;
        }
    }
}