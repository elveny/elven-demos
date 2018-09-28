package site.elven.test.netty.example.codec.protobuf;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
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
                        .addLast(new ProtobufVarint32FrameDecoder())
                        .addLast(new ProtobufDecoder(OrderRequest.OrderRequestProto.getDefaultInstance()))
                        .addLast(new ProtobufVarint32LengthFieldPrepender())
                        .addLast(new ProtobufEncoder())
                        .addLast(new OrderServerHandler());
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
