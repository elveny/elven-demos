package site.elven.test.netty.mine.example.codec.marshalling;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import site.elven.test.netty.common.util.NettyHelper;

public class OrderClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                        .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                        .addLast(new OrderClinetHandler());
            }
        };
        NettyHelper.client(host, port, channelInitializer);

    }
}
