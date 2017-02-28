/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.zeroturnaround.zip.FileSource;
import org.zeroturnaround.zip.NameMapper;
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

//        ZipUtil.pack(new File("D:\\temp\\20170228"), new File("D:\\temp\\xxx.zip"));

        String sourceDir = "/home/elven/下载/temp/20170228";
        String targetZip = "/home/elven/下载/temp/zip/xxx.zip";

        final String prefix = "Test";

        // 生成压缩打包文件
        ZipUtil.pack(new File(sourceDir), new File(targetZip), new NameMapper(){

            @Override
            public String map(String name) {
                return name.startsWith(prefix) ? name : null;
            }
        });

        // 向已有的压缩包中添加文件
        FileSource fileSource = new FileSource("No00001", new File("/home/elven/下载/temp/20170228/No00001"));
        ZipUtil.addEntry(new File(targetZip), fileSource);
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

    /**
     * 1、zip文件需要根据资产包产生，一个资产包产生一个zip
     * 2、同一个资产包一天内多次送给对方的话，需要变化zip文件的序列号，所以zip文件名是有格式的，格式张包含了当天日期和序列号
     * （1）维护一个map，key为“资产包编号”+“_”+“日期”，value为“序列号 1,2,3...999”（增加到999后自动置为1）
     *
     * 3、对账文件filelist需要根据不同的资产包分别生成
     * 4、remove文件也需要在文件名中带上序列号，支持大于10万条分文件
     * 5、根据资产包编号讲文件生成到不同的目录
     */
    @Test
    public void test4() {
    }
}