package site.elven.test.netty.mine.example.quickstart.time;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import site.elven.test.netty.common.util.NettyHelper;

public class TimeClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeClientHandler());
            }
        };
        NettyHelper.client(host, port, channelInitializer);
    }
}
