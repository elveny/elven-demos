/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package site.elven.demos.netty.example.discard;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 * @author qiusheng.wu
 * @Filename DiscardServerHandler.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/12/6 17:05</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
//        ((ByteBuf) msg).release(); // (3)

//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }

        ByteBuf in = (ByteBuf) msg;
        System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
        in.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}