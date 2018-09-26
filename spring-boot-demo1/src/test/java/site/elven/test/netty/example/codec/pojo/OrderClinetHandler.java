package site.elven.test.netty.example.codec.pojo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

import static site.elven.test.util.PrintUtil.println;

public class OrderClinetHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++){
            ctx.writeAndFlush(request("elven", "18888888888", "address", "productName", i));
            ctx.writeAndFlush(request("elveny", "19999999999", "address", "productName", i));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        println("Receive server response: " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private OrderRequest request(String userName, String phoneNumber, String address, String productName, int productNumber){
        return new OrderRequest(UUID.randomUUID().toString(), userName, phoneNumber, address, productName, productNumber);
    }
}
