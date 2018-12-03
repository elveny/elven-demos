/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author qiusheng.wu
 * @Filename ZonedDateTimeTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 22:55</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ZonedDateTimeTester {
    public static void main(String[] args) {
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZonedDateTime date2 = ZonedDateTime.now();
        System.out.println("date2: " + date2);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}