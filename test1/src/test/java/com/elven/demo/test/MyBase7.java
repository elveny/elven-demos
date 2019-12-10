/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test;

import org.junit.Test;
import org.springframework.util.FileCopyUtils;

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
        String myBaseIniFilePath = "D:\\Program Files\\wjjsoft\\nyfedit7\\myBase.ini";
        String myBaseIniBakFilePath = "D:\\Program Files\\wjjsoft\\nyfedit7\\myBase.ini."+ System.currentTimeMillis()+".bak";
        System.out.println("备份成功");
        FileCopyUtils.copy(new File(myBaseIniFilePath), new File((myBaseIniBakFilePath)));
        InputStream is = new BufferedInputStream(new FileInputStream(myBaseIniFilePath));
        Properties properties = new MyProperties();
        properties.load(is);
        System.out.println(properties.getProperty("App.UserLic.FirstUseOn"));
        properties.setProperty("App.UserLic.FirstUseOn", String.valueOf(new Date().getTime()/1000));
        properties.setProperty("App.UserLic.LaunchNum", "1");
        properties.store(new BufferedOutputStream(new FileOutputStream(myBaseIniFilePath)), "");
        System.out.println("执行完成");
    }
}