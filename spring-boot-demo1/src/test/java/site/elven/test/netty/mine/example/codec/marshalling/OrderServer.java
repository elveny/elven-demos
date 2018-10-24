package site.elven.test.netty.mine.example.codec.marshalling;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import site.elven.test.netty.common.util.NettyHelper;

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
                        .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                        .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                        .addLast(new OrderServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
