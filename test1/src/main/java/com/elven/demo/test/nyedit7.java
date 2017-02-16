/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author qiusheng.wu
 * @Filename nyedit7.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/13 18:14</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class nyedit7 {
    private final static String myBasePath = "E:\\temp\\nyedit7\\myBase.ini";
    public static void main(String[] args) throws IOException {
        // load properties
        OrderedProperties properties = new OrderedProperties();
        FileInputStream in = new FileInputStream(myBasePath);
        properties.load(in);
        in.close();

        Iterator<String> it = properties.stringPropertyNames().iterator();
        while (it.hasNext()){
            String key = it.next();
            System.out.println(key+"::::::"+properties.getProperty(key));
        }

        // set properties key
//        properties.setProperty("App.UserLic.FirstUseOn", ""+System.currentTimeMillis()/1000);
//        properties.setProperty("App.UserLic.LaunchNum", "1");
//
//        // store properties
//        FileOutputStream oFile = new FileOutputStream(myBasePath);
//        properties.store(oFile,null);
//        oFile.close();


    }
}