/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jvm.oom;

import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.MethodInterceptor;
import org.assertj.core.internal.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qiusheng.wu
 * @Filename JavaMethodAreaOOM.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/8/8 11:26</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class JavaMethodAreaOOM {
    /**
     * 运行时动态创建类，填满方法区，造成内存溢出。
     * @param args
     */
    public static void main(String[]args){
        while(true){
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor(){
                public Object intercept(Object obj, Method method, Object[]args, MethodProxy proxy)throws Throwable{
                    return proxy.invokeSuper(obj,args);
                }});
            enhancer.create();
        }}
        static class OOMObject{}
}