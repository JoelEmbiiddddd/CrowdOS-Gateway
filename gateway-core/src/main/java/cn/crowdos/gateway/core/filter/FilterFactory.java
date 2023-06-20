package cn.crowdos.gateway.core.filter;

import cn.crowdos.gateway.core.mapping.HttpStatement;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @File : FilterFactory.java
 * @Author : LiXin Huang, NWPU
 * @Date : 2023/6/14
 * @Desc : 过滤器工厂
 * @Email : iHuanglixin@outlook.com
 */

public interface FilterFactory {
    /**
     * 构造过滤器链
     * @param ctx
     * @return
     * @throws Exception
     */
    GatewayFilterChain buildFilterChain(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request,HttpStatement httpStatement) throws Exception;


    /**
     * 通过过滤器id，获取对应的过滤器
     *  <T> T  表示返回值是一个泛型，传递什么，就返回什么类型的数据。  T 表示传递的参数类型。
     * @param filterId
     * @return
     * @param <T>
     * @throws Exception
     */
    <T> T getFilterInfo(String filterId) throws Exception;
}
