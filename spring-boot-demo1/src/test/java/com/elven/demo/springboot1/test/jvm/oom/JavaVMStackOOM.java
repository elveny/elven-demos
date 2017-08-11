/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

/**
 * @author qiusheng.wu
 * @Filename JavaVMStackOOM.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/8 9:20</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while (true){

        }
    }

    /**
     * 模拟无限创建线程，知道无法申请到足够的内存空间，应该抛出OutOfMemoryError。
     */
    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /**
     * ！！！最好别运行，因为会导致电脑死机。
     * @param args
     */
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}