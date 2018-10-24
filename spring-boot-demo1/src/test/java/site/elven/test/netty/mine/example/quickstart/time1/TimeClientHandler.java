package site.elven.test.netty.mine.example.quickstart.time1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static site.elven.test.util.PrintUtil.println;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private int counter ;

    @Override
    public void channelActive(ChannelHandlerContext ctx){

        byte[] req = ("query current time"+System.getProperty("line.separator")).getBytes();
        for (int i = 0; i < 100; i++) {
            ByteBuf message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] message = new byte[buf.readableBytes()];
        buf.readBytes(message);
        String body = new String(message, "UTF-8");
        println("Now is : "+body + "; counter is: "+ ++counter);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        println("exception : "+cause.getMessage());
        ctx.close();
    }
}
