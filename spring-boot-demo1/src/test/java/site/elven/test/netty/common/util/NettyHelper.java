package site.elven.test.netty.common.util;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyHelper {
    private final static Logger logger = LoggerFactory.getLogger(NettyHelper.class);
    public static void server(String name, String host, int port, String successMsg, String failureMsg, ChannelInitializer channelInitializer){
        // 负责接收连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责处理请求数据
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(channelInitializer)
            ;

            ChannelFuture future;
            if(StringUtils.isNotBlank(host)){
                future = bootstrap.bind(host, port).sync();
            }
            else {
                future = bootstrap.bind(port).sync();
            }
            logger.info("服务{}启动成功[host = {}, port = {}] {}", name, host, port, successMsg);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("服务{}启动失败[host = {}, port = {}, exception = {}] {}", name, host, port, e.getMessage(), failureMsg, e);
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void client(String host, int port, ChannelInitializer channelInitializer){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(channelInitializer);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
    }
}
