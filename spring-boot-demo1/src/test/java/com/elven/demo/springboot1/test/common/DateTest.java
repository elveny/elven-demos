/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author qiusheng.wu
 * @Filename DateTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/27 11:38</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DateTest {

    @Test
    public void minusDays(){
        System.out.println(new java.sql.Date(DateTime.now().minusDays(2).toDate().getTime()));
    }

    @Test
    public void calendarTest(){
        Date now = new Date(System.currentTimeMillis()-13*60*60*1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        System.out.println(calendar);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.HOUR));
    }

    @Test
    public void DateTimeTest(){
        System.out.println(new DateTime(new Date(2017, 5, 12, 10, 44, 44).getTime()).getHourOfDay());
        System.out.println(new DateTime(new Date(2017, 5, 12, 10, 44, 44).getTime()).get(DateTimeFieldType.hourOfDay()));
    }

    @Test
    public void test1(){
        DateTime beginDate = new DateTime(new Date()).minusDays(2);//开始日期
        System.out.println(beginDate);

        System.out.println(new java.sql.Date(DateTime.now().minusDays(2).toDate().getTime()));

    }
}