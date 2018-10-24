package site.elven.test.netty.mine.example.codec.msgpack;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import site.elven.test.netty.common.util.NettyHelper;

public class OrderClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                        .addLast("msgpack decoder", new ClientMsgpackDecoder())
                        .addLast(new LengthFieldPrepender(2))
                        .addLast("msgpack encoder", new ClientMsgpackEncoder())
                        .addLast(new OrderClinetHandler());
            }
        };
        NettyHelper.client(host, port, channelInitializer);
    }
}
