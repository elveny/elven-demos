/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * @Filename MyBase7.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-3-8 下午9:48</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MyBase7 {

    /**
     * # vim ~/.myBase7.ini
     * App.UserLic.FirstUseOn=1487605350
     * App.UserLic.LaunchNum=9
     */
    @Test
    public void firstUseOn(){
        long time = new Date().getTime();
        System.out.println(time/1000);
    }

    @Test
    public void auto() throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream("D:\\Program Files\\wjjsoft\\nyfedit7\\myBase.ini"));
        Properties properties = new MyProperties();
        properties.load(is);
        System.out.println(properties.getProperty("App.UserLic.FirstUseOn"));
        properties.setProperty("App.UserLic.FirstUseOn", String.valueOf(new Date().getTime()/1000));
        properties.setProperty("App.UserLic.LaunchNum", "1");
        properties.store(new BufferedOutputStream(new FileOutputStream("D:\\Program Files\\wjjsoft\\nyfedit7\\myBase.ini")), "");
    }
}