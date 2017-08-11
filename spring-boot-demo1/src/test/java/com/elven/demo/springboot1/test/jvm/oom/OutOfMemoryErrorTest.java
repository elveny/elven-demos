/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename OutOfMemoryErrorTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/19 14:22</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class OutOfMemoryErrorTest {

    @Test
    public void test1(){
        System.out.println("//////////test1 start//////////");

        try {
            List l = new ArrayList();
            while(true){
                JavaHeapOverFlow f = new JavaHeapOverFlow();
                l.add(f);
            }
        }
        catch (OutOfMemoryError e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }

        System.out.println("//////////test1 end//////////");

    }

    @Test
    public void test2(){
        System.out.println("//////////test2 start//////////");

        try {
            int i = 0;
            //保持对常量池的应用，避免被full gc回收
            List<String> l = new ArrayList<String>();
            while(true) {
                l.add(String.valueOf(i++).intern());
            }
        }
        catch (OutOfMemoryError e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }

        System.out.println("//////////test2 end//////////");
    }

    @Test
    public void test3(){
        System.out.println("//////////test3 start//////////");

        try {
            Field f = Unsafe.class.getDeclaredFields()[0];
            f.setAccessible(true);
            Unsafe d =(Unsafe)f.get(null);
            while(true){
                d.allocateMemory(1024*1024);
            }

        }
        catch (OutOfMemoryError | IllegalAccessException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }

        System.out.println("//////////test3 end//////////");
    }
}