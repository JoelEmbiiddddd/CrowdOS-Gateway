package cn.crowdos.gateway.core.socket;

import cn.crowdos.gateway.core.session.Configuration;
import cn.crowdos.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;


/**
 * @File : GatewaySocketServer.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关会话服务
 * @Email : iHuanglixin@outlook.com
 */

public class GatewaySocketServer implements Callable<Channel> {

    private final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);

    private final Configuration configuration;
    private DefaultGatewaySessionFactory gatewaySessionFactory;

    private EventLoopGroup boss;
    private EventLoopGroup work;
    private Channel channel;

    public GatewaySocketServer(Configuration configuration, DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
        this.initEventLoopGroup();
    }

    private void initEventLoopGroup(){
        boss = new NioEventLoopGroup(configuration.getBossNThreads());
        work = new NioEventLoopGroup(configuration.getWorkNThreads());
    }

    @Override
    public Channel call() throws Exception {

        ChannelFuture channelFuture = null;
        try {
            // 创建服务端实例
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 服务端收到新的请求的时候处理用的
                    .childHandler(new GatewayChannelInitializer(configuration, gatewaySessionFactory));
            // Docker 容器部署会自动分配IP，所以我们只设定端口即可。并进行Netty的异步编程
            channelFuture = b.bind(new InetSocketAddress(configuration.getHostName(), configuration.getPort())).syncUninterruptibly();
            // channelFuture = b.bind(configuration.getPort()).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            logger.error("socket server start error.", e);
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket server start done.");
            } else {
                logger.error("socket server start error.");
            }
        }
        return channel;
    }

}
