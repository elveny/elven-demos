package site.elven.test.netty.mine.example.codec.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

import static site.elven.test.util.PrintUtil.println;

public class OrderServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        OrderRequest.OrderRequestProto request = (OrderRequest.OrderRequestProto) msg;

        println("Server receive client pojo request:" + request);

        OrderResponse.OrderResponseProto response = null;
        if(StringUtils.equalsIgnoreCase("elven", request.getUserName())){
            response = responseSuccess(request.getOrderNo());
        }
        else {
            response = responseFailure(request.getOrderNo());
        }
        ctx.writeAndFlush(response);
    }

    private OrderResponse.OrderResponseProto responseSuccess(String orderNo){
        OrderResponse.OrderResponseProto.Builder builder = OrderResponse.OrderResponseProto.newBuilder();
        builder.setOrderNo(orderNo);
        builder.setCode("0000");
        builder.setMessage("success");
        return builder.build();
    }

    private OrderResponse.OrderResponseProto responseFailure(String orderNo){
        OrderResponse.OrderResponseProto.Builder builder = OrderResponse.OrderResponseProto.newBuilder();
        builder.setOrderNo(orderNo);
        builder.setCode("0000");
        builder.setMessage("failure");
        return builder.build();
    }
}
