/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.demos.netty.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qiusheng.wu
 * @Filename NioCopyFile.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/7 11:33</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class NioCopyFile {

    public void copyFile(String src, String dist) throws IOException {
        // 创建源文件和目标文件流
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);

        // 获取Channel
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        // 创建缓存
        ByteBuffer buffer = ByteBuffer.allocate(2);

        // 将Channel中的数据读取到缓存
        while (inChannel.read(buffer) != -1){
            // 切换到读模式
            buffer.flip();

            // 将缓存中的数据写入Channel
            outChannel.write(buffer);

            // 重设缓存
            buffer.clear();
        }

        // 关闭Channel
        inChannel.close();
        outChannel.close();

        // 关闭文件流
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new NioCopyFile().copyFile("E:\\code\\idea\\elven\\elven-demos\\netty-demo\\src\\main\\java\\site\\elven\\demos\\netty\\io\\iotest.txt", "E:\\code\\idea\\elven\\elven-demos\\netty-demo\\src\\main\\java\\site\\elven\\demos\\netty\\io\\iotest2.txt");
    }

}