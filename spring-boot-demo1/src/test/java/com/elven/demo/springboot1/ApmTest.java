/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename ApmTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/28 17:19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ApmTest {

    @Test
    public void test1(){

        ZipUtil.pack(new File("D:\\temp\\20170228"), new File("D:\\temp\\xxx.zip"));
    }

    @Test
    public void test2() throws IOException {

        Collection<File> files = FileUtils.listFiles(new File("D:\\temp\\20170228"), null, false);
        Iterator<File> it = files.iterator();
        List<String> lines = new ArrayList<String>();
        while (it.hasNext()){
            lines.add(it.next().getName());
        }

        IOUtils.writeLines(lines, null, new FileOutputStream("D:\\temp\\20170228\\"+new DateTime().toString("yyyyMMdd")+"_FileList.txt"), Charset.forName("UTF-8"));
    }

    @Test
    public void test3() throws IOException {
        Collection<File> files = FileUtils.listFiles(new File("D:\\temp\\20170228"), null, false);
        Iterator<File> it = files.iterator();
        List<String> lines = new ArrayList<String>();
        while (it.hasNext()){
            lines.add(it.next().getName());
        }
        System.out.println(lines);
    }
}