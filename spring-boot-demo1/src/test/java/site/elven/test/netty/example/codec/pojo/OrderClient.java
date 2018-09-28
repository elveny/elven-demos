package site.elven.test.netty.example.codec.pojo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import site.elven.test.netty.common.utils.NettyHelper;

public class OrderClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
                        .addLast(new ObjectEncoder())
                        .addLast(new OrderClinetHandler());
            }
        };
        NettyHelper.client(host, port, channelInitializer);

    }
}
