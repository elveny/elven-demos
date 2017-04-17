/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.apm;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.zeroturnaround.zip.FileSource;
import org.zeroturnaround.zip.NameMapper;
import org.zeroturnaround.zip.ZipUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

        String filePath = "D:\\temp\\20170228\\F1-TEST-2\\";
        Collection<File> files = FileUtils.listFiles(new File(filePath), null, false);
        Iterator<File> it = files.iterator();
        List<String> lines = new ArrayList<String>();
        while (it.hasNext()){
            lines.add(it.next().getName());
        }

        FileUtils.writeLines(new File(filePath+new DateTime().toString("yyyyMMdd")+"_FileList.txt"), "utf-8", lines, null);
//        IOUtils.writeLines(lines, null, new FileOutputStream(filePath+new DateTime().toString("yyyyMMdd")+"_FileList.txt"), Charset.forName("UTF-8"));
    }

    @Test
    public void test2_1() throws IOException {

        String sourceDir = "D:\\temp\\20170228\\F1-TEST-2\\";
        String targetZip = "D:\\temp\\20170228\\F1-TEST-2_20170228001.zip";

        ZipUtil.pack(new File(sourceDir), new File(targetZip));

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

    @Test
    public void test5(){
        String filePath = "D:\\temp\\20170228\\F1-TEST-1\\F1-TEST-1_20170228001_Contract.txt";
        File file = new File(filePath);
    }

    @Test
    public void test6() {
        String str = StringUtils.leftPad("11", 3, "0");
        System.out.println(str);
    }

    @Test
    public void test7(){
        String filePath = "D"+File.separator+"aaaaa"+File.separator+"bbbbb"+File.separator+"ccccc"+File.separator+"ddddd";
        System.out.println(filePath.substring(filePath.lastIndexOf(File.separator)+1));
    }

    @Test
    public void test8() throws Exception {
        String encoding = codeString("C:\\Users\\qiusheng.wu\\Downloads\\20170307\\20170307_Filelist.txt");
//        String encoding = codeString("C:\\Users\\qiusheng.wu\\Downloads\\20170307\\F1-TEST-1_20170307001_Contract.txt");
        System.out.println(encoding);
    }

    @Test
    public void test9(){

        String csn = Charset.defaultCharset().name();
        System.out.println("字符集："+csn);
    }

    public String codeString(String fileName) throws Exception{
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII" ;
                break ;
            default:
                code = "GBK";
        }

        return code;
    }
}