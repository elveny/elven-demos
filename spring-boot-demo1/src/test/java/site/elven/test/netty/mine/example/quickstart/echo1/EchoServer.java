package site.elven.test.netty.mine.example.quickstart.echo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
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
                ch.pipeline()
                        .addLast(new FixedLengthFrameDecoder(10))
                        .addLast(new StringDecoder())
                        .addLast(new EchoServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
