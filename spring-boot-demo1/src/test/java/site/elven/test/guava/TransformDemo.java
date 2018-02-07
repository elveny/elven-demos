/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author qiusheng.wu
 * @Filename TransformDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:48</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TransformDemo {
    public static void main(String[] args) {
        transform1(args);
        transform2(args);
    }
    public static void transform1(String[] args) {
        Set<Long> times= Sets.newHashSet();
        times.add(91299990701L);
        times.add(9320001010L);
        times.add(9920170621L);
        Collection<String> timeStrCol= Collections2.transform(times, new Function<Long, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Long input) {
                return new SimpleDateFormat("yyyy-MM-dd").format(input);
            }
        });
        System.out.println(timeStrCol);
    }



    public static void transform2(String[] args) {
        List<String> list= Lists.newArrayList("abcde","good","happiness");
        //确保容器中的字符串长度不超过5
        Function<String,String> f1=new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.length()>5?input.substring(0,5):input;
            }
        };
        //转成大写
        Function<String,String> f2=new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.toUpperCase();
            }
        };
        Function<String,String> function= Functions.compose(f1,f2);
        Collection<String> results=Collections2.transform(list,function);
        System.out.println(results);
    }

}