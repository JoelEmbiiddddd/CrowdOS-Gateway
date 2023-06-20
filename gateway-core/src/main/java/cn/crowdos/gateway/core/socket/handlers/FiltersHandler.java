package cn.crowdos.gateway.core.socket.handlers;

import cn.crowdos.gateway.core.filter.FilterFactory;
import cn.crowdos.gateway.core.filter.GatewayFilterChainFactory;
import cn.crowdos.gateway.core.mapping.HttpStatement;
import cn.crowdos.gateway.core.session.Configuration;
import cn.crowdos.gateway.core.socket.BaseHandler;
import cn.crowdos.gateway.core.socket.agreement.AgreementConstants;
import cn.crowdos.gateway.core.socket.agreement.GatewayResultMessage;
import cn.crowdos.gateway.core.socket.agreement.RequestParser;
import cn.crowdos.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @File : FiltersHandler.java
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class FiltersHandler extends BaseHandler<FullHttpRequest> {
    private final Logger logger = LoggerFactory.getLogger(ProtocolDataHandler.class);

    private final Configuration configuration;

    private FilterFactory filterFactory = GatewayFilterChainFactory.getInstance();

    public FiltersHandler(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request) throws Exception {
        logger.info("网关接收请求【全局】 uri：{} method：{}", request.uri(), request.method());

        HttpStatement httpStatement = channel.attr(AgreementConstants.HTTP_STATEMENT).get();
        try{
            // 1. 解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if (null == uri) {
                return;
            }

            // 2. 调用过滤器链
            filterFactory.buildFilterChain(ctx,  channel, request, httpStatement);

            // 成功后放行
            request.retain();
            ctx.fireChannelRead(request);

        }catch (Exception e){
            // 4. 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(), "对不起，访问内容有误，请重新进行处理！"));
            channel.writeAndFlush(response);
        }




    }
}
