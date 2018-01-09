/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.zip.ZipInputStream;

/**
 * @author qiusheng.wu
 * @Filename ZipTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/5 16:51</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ZipTest {

    @Test
    public void upZipTest() throws IOException {

        InputStream inputStream = new FileInputStream(ResourceUtils.getFile("D:\\work\\msfinance\\支付平台2.0\\SVN\\EngineeringDocument工程文档区\\1Develop开发区\\2Design设计\\2.0\\京东对账\\20160823ordercreate_22294531001.zip"));
//        InputStream inputStream = new FileInputStream(ResourceUtils.getFile("D:\\work\\msfinance\\支付平台2.0\\SVN\\EngineeringDocument工程文档区\\1Develop开发区\\2Design设计\\2.0\\京东对账\\1111111\\1111111.zip"));

        byte[] bytes = IOUtils.toByteArray(inputStream);
        System.out.println(bytes.length);

        byte[] upzips = unZipEntry(bytes);
        System.out.println(upzips.length);

    }

    public static byte[] unZipEntry(byte[] zipData) {
        byte[] result = null;
        ByteArrayInputStream bais = null;
        ZipInputStream zipis = null;
        ByteArrayOutputStream baos = null;
        try {
            bais = new ByteArrayInputStream(zipData);
            zipis = new ZipInputStream(bais);
            while (zipis.getNextEntry() != null) {
                byte[] buffer = new byte[1024];
                int num = -1;
                baos = new ByteArrayOutputStream();
                while ((num = zipis.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, num);
                }
                result = baos.toByteArray();
                baos.flush();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(baos != null){
                    baos.close();
                }
                if(zipis != null){
                    zipis.close();
                }
                if(bais != null){
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}