/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import com.elven.demo.springboot1.common.util.POIUtil;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename POITest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/20 10:59</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class POITest {

    @Test
    public void test1() throws IOException {
        Map<String, List<String[]>> data = POIUtil.parse(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\20170118.xls"));

        for(String key : data.keySet()){
            System.out.println("key:::::"+key);

            List<String[]> list = data.get(key);
            for(String[] strs : list){
                for(String str : strs){
                    System.out.print(str+" ");
                }
                System.out.println();
            }

            System.out.println(":::::::::::::::::::::::end::::::::::::::::"+key);
        }
    }
}