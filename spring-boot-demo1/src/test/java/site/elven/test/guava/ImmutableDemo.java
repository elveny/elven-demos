/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * @author qiusheng.wu
 * @Filename ImmutableDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:06</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ImmutableDemo {
    public static void main(String[] args) {
        ImmutableSet<String> set=ImmutableSet.of("a","b","c","d");
        ImmutableSet<String> set1=ImmutableSet.copyOf(set);
        ImmutableSet<String> set2=ImmutableSet.<String>builder().addAll(set).add("e").build();
        System.out.println(set);
        ImmutableList<String> list=set.asList();
    }
}