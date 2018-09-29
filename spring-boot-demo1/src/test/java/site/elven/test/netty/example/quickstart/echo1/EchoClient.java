package site.elven.test.netty.example.quickstart.echo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import site.elven.test.netty.common.utils.NettyHelper;

public class EchoClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new FixedLengthFrameDecoder(10))
                        .addLast(new StringDecoder())
                        .addLast(new EchoClinetHandler());
            }
        };
        NettyHelper.client(host, port, channelInitializer);
    }
}
