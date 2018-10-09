package site.elven.test.netty.example.privateprotocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import site.elven.test.netty.common.utils.NettyHelper;

public class PrivateProtocolClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0))
                        .addLast(new NettyMessageEncoder())
                        .addLast(new ReadTimeoutHandler(50))
                        .addLast(new LoginAuthReqHandler())
                        .addLast(new HeartBeatReqHandler())
                ;
            }
        };
        NettyHelper.client(host, port, channelInitializer);

    }
}
