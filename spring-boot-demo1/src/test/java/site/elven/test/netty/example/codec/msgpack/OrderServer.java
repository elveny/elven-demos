package site.elven.test.netty.example.codec.msgpack;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import site.elven.test.netty.common.utils.NettyHelper;

public class OrderServer {
    public static void main(String[] args) {
        String name = "OrderServer";
        String host = null;
        int port = 8080;
        String successMsg = "";
        String failureMsg = "";
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline()
                        .addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                        .addLast("msgpack decoder", new ServerMsgpackDecoder())
                        .addLast(new LengthFieldPrepender(2))
                        .addLast("msgpack encoder", new ServerMsgpackEncoder())
                        .addLast(new OrderServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
