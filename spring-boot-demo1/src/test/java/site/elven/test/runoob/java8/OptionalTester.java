/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

import java.util.Optional;

/**
 * @author qiusheng.wu
 * @Filename OptionalTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 18:41</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class OptionalTester {
    public static void main(String[] args) {
        OptionalTester optionalTester = new OptionalTester();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(optionalTester.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}