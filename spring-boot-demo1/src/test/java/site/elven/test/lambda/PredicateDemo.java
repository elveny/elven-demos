/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author qiusheng.wu
 * @Filename PredicateDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/2 17:25</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class PredicateDemo {
    public static void main(String[] args){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.startsWith("J"));
        System.out.println("Languages which starts with J :");
        filterBetter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.endsWith("a"));
        System.out.println("Languages which ends with a ");
        filterBetter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);
        System.out.println("Print all languages :");
        filterBetter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);
        System.out.println("Print no language : ");
        filterBetter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.length() > 4);
        System.out.println("Print language whose length greater than 4:");
        filterBetter(languages, (str)->str.length() > 4);

        filterAndOr(languages);
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    // 更好的办法
    public static void filterBetter(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    // 更好的办法
    public static void filterAndOr(List<String> names) {
        // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("which starts with 'J' and four letter long is : " + n));
    }
}