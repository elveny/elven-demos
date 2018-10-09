package site.elven.test.netty.example.privateprotocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import site.elven.test.netty.common.utils.NettyHelper;

import java.io.IOException;

public class PrivateProtocolServer {
    public static void main(String[] args) {
        String name = "PrivateProtocolServer";
        String host = null;
        int port = 8080;
        String successMsg = "";
        String failureMsg = "";
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws IOException {
                ch.pipeline()
                        .addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0))
                        .addLast(new NettyMessageEncoder())
                        .addLast(new ReadTimeoutHandler(50))
                        .addLast(new LoginAuthRespHandler())
                        .addLast(new HeartBeatRespHandler())
                ;

            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
