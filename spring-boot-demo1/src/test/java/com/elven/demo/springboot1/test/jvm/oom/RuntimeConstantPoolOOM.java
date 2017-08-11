/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

/**
 * @author qiusheng.wu
 * @Filename RuntimeConstantPoolOOM.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/8 11:18</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM{
    /**
     * Java 1.7之后便不会造成内存溢出了。
     * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
     * @param args
     */
    public static void main(String[]args){
//使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list=new ArrayList<String>();
//10MB的PermSize在integer范围内足够产生OOM了
        int i=0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}