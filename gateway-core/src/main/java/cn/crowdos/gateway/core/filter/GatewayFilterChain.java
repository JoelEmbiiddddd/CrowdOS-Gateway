package cn.crowdos.gateway.core.filter;

import java.util.ArrayList;
import java.util.List;
import cn.crowdos.gateway.core.filter.GatewayContext;
import cn.crowdos.gateway.core.mapping.HttpStatement;
import cn.crowdos.gateway.core.socket.GatewaySocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @File : GatewayFilterChain.java
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class GatewayFilterChain  {


    private final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);

    /**
     * 过滤器集合
     */
    private List<Filter> filterList = new ArrayList<>();

    /**
     * 向过滤器链中添加过滤器
     * @param filter
     * @return
     */
    public GatewayFilterChain addFilter(Filter filter) {
        filterList.add(filter);
        return this;
    }

    /**
     * 向过滤器链中添加过滤器
     * @param filters
     * @return
     */
    public GatewayFilterChain addFilterList (List<Filter> filters) {
        filterList.addAll(filters);
        return this;
    }

    /**
     * 过滤
     * @param ctx
     * @return
     * @throws Throwable
     */
    public HttpStatement doFilter(HttpStatement ctx) {
        if (filterList.isEmpty()) {
            return ctx;
        }
        try {
            for (Filter filter : filterList) {
                filter.doFilter(ctx);
            }
        } catch (Exception e) {
            // 发生异常后处理
            logger.error("执行过滤器发生异常,异常信息：{}", e.getMessage());
        }
        return ctx;
    }
}
