/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.demos.netty.io;

import java.io.*;

/**
 * @author qiusheng.wu
 * @Filename FileReaderTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/7 8:52</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class IoFileReaderTest {

    public static void main(String[] args) throws IOException {

        // （1）创建文件InputStream
        InputStream in = new BufferedInputStream(new FileInputStream("E:\\code\\idea\\elven\\elven-demos\\netty-demo\\src\\main\\java\\site\\elven\\demos\\netty\\io\\iotest.txt"));

        // （2）初始化缓存，并为缓存分配空间
        byte[] buf = new byte[2];
        String result = "";

        int bytesRead = -1;
        // （3）循环读取InputStream中的数据并放入缓存buf
        while ((bytesRead = in.read(buf)) != -1){
            // （4）从缓存buf中取出数据进行处理（比如转成字符打印出来等等）
            System.out.println("此次读取字节数："+bytesRead);
            String r = new String(buf);
            System.out.println("本次读取字符"+r);
            result += r;
            buf = new byte[2];
        }
        System.out.println("读取文件内容："+result);

    }
}