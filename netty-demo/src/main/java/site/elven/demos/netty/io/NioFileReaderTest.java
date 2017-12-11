/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.demos.netty.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qiusheng.wu
 * @Filename FileReaderTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/7 10:20</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class NioFileReaderTest {
    public static void main(String[] args) throws IOException {
        // （1）创建文件InputStream
        FileInputStream fin = new FileInputStream("E:\\code\\idea\\elven\\elven-demos\\netty-demo\\src\\main\\java\\site\\elven\\demos\\netty\\io\\iotest.txt");

        // （2）获取文件Channel
        FileChannel fc = fin.getChannel();

        // （3）创建ByteBuffer并分配空间
        ByteBuffer buffer = ByteBuffer.allocate(2);

        String result = "";
        int bufferReadSize = -1;
        // （4）循环将文件Channel中的数据读取到ByteBuffer
        while ((bufferReadSize = fc.read(buffer)) != -1){
            System.out.println("本次读取字节数："+bufferReadSize);
            // （5）buffer.flip()切换读取模式（实际是设置相关参数的值limit = position; position = 0; mark = -1;）
            buffer.flip();
            int size = buffer.limit();
            byte[] bytes = new byte[size];
            int i = 0;
            // （6）循环将ByteBuffer中的字节取出来，并做相应处理（比如转成字符打印出来等等）
            while (buffer.hasRemaining()){
                byte b = buffer.get();
                bytes[i] = b;
                i++;
            }
            String r = new String(bytes);
            result += r;
            System.out.println("本次读取："+r);
            buffer.clear();
        }
        System.out.println("读取文件内容："+result);
    }
}