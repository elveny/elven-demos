package site.elven.test.netty.mine.example.quickstart.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import site.elven.test.netty.common.util.NettyHelper;

public class EchoServer {
    public static void main(String[] args) {
        String name = "EchoServer";
        String host = null;
        int port = 8080;
        String successMsg = "";
        String failureMsg = "";
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) {
                ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                ch.pipeline()
                        .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                        .addLast(new StringDecoder())
                        .addLast(new EchoServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
