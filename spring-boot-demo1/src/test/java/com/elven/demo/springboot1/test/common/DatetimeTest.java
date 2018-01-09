/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author qiusheng.wu
 * @Filename DatetimeTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/1 13:03</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DatetimeTest {
    @Test
    public void test1(){
//        String now = (new SimpleDateFormat("yyyyMMddHHmmss"))
//                .format(DateTime.now().minusDays(1).toDate());
//
//        System.out.println(DateTime.now().minusDays(1).toDate());
//        System.out.println(Date.valueOf("2017-12-31"));
//        System.out.println(now);

        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2016-1-1")));
        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2015-12-28")));
        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2015-12-29")));
        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2015-12-30")));
        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2015-12-31")));

        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2015-1-1")));
        System.out.println(new SimpleDateFormat("YYYYMMdd").format(Date.valueOf("2014-12-31")));
    }
}