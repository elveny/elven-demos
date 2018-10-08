package site.elven.test.netty.example.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import site.elven.test.netty.common.utils.NettyHelper;

public class WebSocketServer {

    public static void main(String[] args) {
        {
            String name = "WebSocketServer";
            String host = null;
            int port = 8080;
            String successMsg = "请在浏览器中访问：http://localhost:"+port+"/";
            String failureMsg = "";

            ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast("http-decoder", new HttpServerCodec())
                            .addLast("http-aggregator", new HttpObjectAggregator(65536))
                            .addLast("http-chunk", new ChunkedWriteHandler())
                            .addLast("handler", new WebSocketServerHandler());
                }
            };

            NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
        }
    }
}
