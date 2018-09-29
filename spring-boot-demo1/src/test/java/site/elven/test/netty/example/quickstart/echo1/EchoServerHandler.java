package site.elven.test.netty.example.quickstart.echo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static site.elven.test.util.PrintUtil.println;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private int counter = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        println("This is " + ++counter + " times receive client: " + body);
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);

    }
}
