/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.demos.netty.io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author qiusheng.wu
 * @Filename ByteBufferTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/26 11:01</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ByteBufferTest {

    public static void main(String[] args) {

//        allocate();

        put();

    }

    public static void allocate(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.get());
        System.out.println(buffer.array().length);

        buffer = ByteBuffer.wrap(new byte[]{1, 2, 3});
        System.out.println(buffer.get());
        System.out.println(buffer.array().length);

        buffer = ByteBuffer.wrap(new byte[]{Byte.parseByte("22"), 3});
        System.out.println(buffer.get());
        System.out.println(buffer.array().length);
    }


    public static void put(){
        IntBuffer buffer = IntBuffer.allocate(1024);
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        System.out.println(buffer.array()[1]);

        System.out.println(buffer.get());

        buffer.flip();

        System.out.println(buffer.get());
        buffer.mark();
        System.out.println(buffer.get());
        System.out.println(buffer.get());

        buffer.reset();
        System.out.println(buffer.get());

    }
}