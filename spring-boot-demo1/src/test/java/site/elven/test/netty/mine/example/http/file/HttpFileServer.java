package site.elven.test.netty.mine.example.http.file;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.elven.test.netty.common.util.NettyHelper;

public class HttpFileServer {
    private final static Logger logger = LoggerFactory.getLogger(NettyHelper.class);
    public static void main(String[] args) {
        String name = "HttpFileServer";
        String host = null;
        int port = 8080;
        String successMsg = "请在浏览器中访问：http://localhost:"+port+"/test1/src/";
        String failureMsg = "";

        ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast("http-decoder", new HttpRequestDecoder())
                        .addLast("http-aggregator", new HttpObjectAggregator(65536))
                        .addLast("http-encoder", new HttpResponseEncoder())
                        .addLast("http-chunk", new ChunkedWriteHandler())
                        .addLast("fileServerHandler", new HttpFileServerHandler("/test1/src"));
            }
        };

        NettyHelper.server(name, host, port, successMsg, failureMsg, channelInitializer);
    }
}
