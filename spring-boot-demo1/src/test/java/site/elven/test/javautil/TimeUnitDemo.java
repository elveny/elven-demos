/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.javautil;

import java.util.concurrent.TimeUnit;

/**
 * @author qiusheng.wu
 * @Filename TimeUnitDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 21:09</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TimeUnitDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(TimeUnit.valueOf("NANOSECONDS"));
        System.out.println(Enum.valueOf(TimeUnit.class, "MICROSECONDS"));
        System.out.println(TimeUnit.valueOf(TimeUnit.class, "MICROSECONDS"));
        System.out.println(TimeUnit.SECONDS.convert(100000000L, TimeUnit.MICROSECONDS));
        System.out.println(TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES));
        System.out.println(TimeUnit.SECONDS.convert(1000L, TimeUnit.MILLISECONDS));
        System.out.println(TimeUnit.SECONDS.toDays(100000L));
        System.out.println("i will sleep 1 second");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("i'm wake up");

        TimeUnit.SECONDS.timedJoin(new Thread(() ->{
            System.out.println("...");
        }), 10);

        new Thread(() ->{
            System.out.println("......");
        }).start();

    }
}