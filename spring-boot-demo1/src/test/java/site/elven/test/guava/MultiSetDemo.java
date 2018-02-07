/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author qiusheng.wu
 * @Filename MultiSetDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:23</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MultiSetDemo {

    /**
     * Multiset可统计一个词在文档中出现了多少次
     * @param args
     */
    public static void main(String[] args) {
        Multiset<String> set= LinkedHashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("中午");
        set.add("a");
        set.add("a");

        System.out.println(set);

        set.setCount("a",5); //添加或删除指定元素使其在集合中的数量是count
        System.out.println(set.count("a")); //给定元素在Multiset中的计数
        System.out.println(set.count("中午")); //给定元素在Multiset中的计数
        System.out.println(set);
        System.out.println(set.size()); //所有元素计数的总和,包括重复元素
        System.out.println(set.elementSet().size()); //所有元素计数的总和,不包括重复元素
        set.clear(); //清空集合
        System.out.println(set);

    }
}