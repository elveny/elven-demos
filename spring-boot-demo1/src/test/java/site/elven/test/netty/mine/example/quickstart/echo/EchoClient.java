package site.elven.test.netty.mine.example.quickstart.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import site.elven.test.netty.common.util.NettyHelper;

public class EchoClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                ch.pipeline()
                        .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                        .addLast(new StringDecoder())
                        .addLast(new EchoClinetHandler());
            }
        };
        NettyHelper.client(host, port, channelInitializer);

    }
}
