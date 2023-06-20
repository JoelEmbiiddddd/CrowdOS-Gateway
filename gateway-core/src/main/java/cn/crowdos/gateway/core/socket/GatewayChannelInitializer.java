package cn.crowdos.gateway.core.socket;

import cn.crowdos.gateway.core.session.Configuration;
import cn.crowdos.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.crowdos.gateway.core.socket.handlers.AuthorizationHandler;
import cn.crowdos.gateway.core.socket.handlers.FiltersHandler;
import cn.crowdos.gateway.core.socket.handlers.GatewayServerHandler;
import cn.crowdos.gateway.core.socket.handlers.ProtocolDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @File : GatewayChannelInitializer.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 会话管道初始化类
 * @Email : iHuanglixin@outlook.com
 */

public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel>{

    private final Configuration configuration;
    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayChannelInitializer(Configuration configuration, DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline line = channel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new GatewayServerHandler(configuration));
        line.addLast(new AuthorizationHandler(configuration));
        line.addLast(new FiltersHandler(configuration));
        line.addLast(new ProtocolDataHandler(gatewaySessionFactory));
    }
}
