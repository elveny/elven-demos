/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.demos.netty.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author qiusheng.wu
 * @Filename TimeServerHandler.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/6 23:00</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener((ChannelFutureListener) future -> {
            assert f == future;
            ctx.close();
        }); // (4)

        // 简单方式
//        f.addListener(ChannelFutureListener.CLOSE); // 功能同(4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}