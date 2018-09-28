package site.elven.test.netty.example.time;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import site.elven.test.netty.common.utils.NettyHelper;

public class TimeServer {
    public static void main(String[] args) {
        String name = "TimeServer";
        String host = null;
        int port = 8080;
        String successMsg = "";
        String failureMsg = "";
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new TimeServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
