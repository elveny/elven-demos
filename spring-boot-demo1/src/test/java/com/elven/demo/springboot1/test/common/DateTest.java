/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.joda.time.DateTime;
import org.junit.Test;

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

}