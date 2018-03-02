/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author qiusheng.wu
 * @Filename BaseStreamDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/27 10:26</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class BaseStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {

//        new BaseStreamDemo().createStreamTest();
        new BaseStreamDemo().streamOperator();
    }

    public void createStreamTest() throws FileNotFoundException {
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println(list);

        Stream<String> stringStream = list.stream();
        stringStream = list.parallelStream();

        stringStream = Arrays.stream(new String[]{"1", "2", "3"});
        stringStream = Stream.of("1", "2", "3");
        stringStream = Stream.of("1");

        BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\code\\idea\\elven\\elven-demos\\netty-demo\\src\\main\\java\\site\\elven\\demos\\netty\\io\\iotest.txt"));
        stringStream = bufferedReader.lines();

        IntStream intStream = IntStream.range(1, 127);

    }

    public void streamOperator(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        System.out.println(list.stream().count());
        System.out.println(list.stream().filter(s -> s>6).count());
        System.out.println(list.stream().filter(s -> s>6).collect(Collectors.toList()));
        System.out.println(list.stream().filter(s -> s>6).limit(1).findAny());
    }
}