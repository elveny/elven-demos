package site.elven.test.netty.example.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static site.elven.test.util.PrintUtil.println;

public class EchoClinetHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    private final static String HELLO = "hi, elven.$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(HELLO.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        println("This is " + ++counter + " times receive server: " + msg);

    }
}
