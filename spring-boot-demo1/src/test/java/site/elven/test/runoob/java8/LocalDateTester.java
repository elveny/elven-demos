/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * @author qiusheng.wu
 * @Filename LocalDateTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 22:51</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class LocalDateTester {
    public static void main(String[] args) {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        LocalTime date6 = LocalTime.parse("000000", DateTimeFormatter.ofPattern("HHmmss"));
        System.out.println("date6: " + date6);

        LocalTime date7 = LocalTime.parse("235959", DateTimeFormatter.ofPattern("HHmmss"));
        System.out.println("date7: " + date7);
        System.out.println(date6.compareTo(date7));


        LocalTime date8 = LocalTime.parse("090000", DateTimeFormatter.ofPattern("HHmmss"));
        System.out.println("date8: " + date8);

        LocalTime date9 = LocalTime.parse("160600", DateTimeFormatter.ofPattern("HHmmss"));
        System.out.println("date9: " + date9);
        System.out.println(LocalTime.now().withSecond(0).withNano(0));
        System.out.println(LocalTime.now().withSecond(0).withNano(0).compareTo(date9));
    }

}