package site.elven.test.netty.mine.example.codec.msgpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

import static site.elven.test.util.PrintUtil.println;

public class OrderServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        OrderRequest request = (OrderRequest) msg;

        println("Server receive client pojo request:" + request);

        OrderResponse response = null;
        if(StringUtils.equalsIgnoreCase("elven", request.getUserName())){
            response = responseSuccess(request.getOrderNo());
        }
        else {
            response = responseFailure(request.getOrderNo());
        }
        ctx.writeAndFlush(response);
    }

    private OrderResponse responseSuccess(String orderNo){
        return new OrderResponse(orderNo, "0000", "success");
    }

    private OrderResponse responseFailure(String orderNo){
        return new OrderResponse(orderNo, "9999", "failure");
    }
}
