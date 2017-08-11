/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename HeapOOM.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/7 20:32</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class HeapOOM {
    static class OOMObject{

    }

    /**
     * 模拟无限创建对象，并不释放内存，最终抛出异常。
     * VM options: -Xms20M -Xmx20M
     * 抛出异常：java.lang.OutOfMemoryError: Java heap space
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}