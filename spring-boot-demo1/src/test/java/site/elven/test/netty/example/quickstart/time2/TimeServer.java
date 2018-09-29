package site.elven.test.netty.example.quickstart.time2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import site.elven.test.netty.common.utils.NettyHelper;

public class TimeServer {
    public static void main(String[] args) {
        String name = "TimeServer2";
        String host = null;
        int port = 8080;
        String successMsg = "";
        String failureMsg = "";
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024))
                        .addLast(new StringDecoder())
                        .addLast(new TimeServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
