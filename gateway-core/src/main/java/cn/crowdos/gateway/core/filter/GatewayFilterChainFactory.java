package cn.crowdos.gateway.core.filter;

import cn.crowdos.gateway.core.mapping.FilterConst;
import cn.crowdos.gateway.core.mapping.FilterRule;
import cn.crowdos.gateway.core.mapping.HttpStatement;
import cn.crowdos.gateway.core.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @File : GatewayFilterChainFactory.java
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class GatewayFilterChainFactory implements FilterFactory{

    private final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);

    public Map<String /* filterId */, Filter> processorFilterIdMap = new ConcurrentHashMap<>();
    /**
     * 将单例对象的定义放在静态内部类 SingletonInstance 中
     * 静态内部类并不会随着外部类的加载一起加载，只有在使用时才会加载；
     * 类加载的过程则直接保证了线程安全性，保证实例对象的唯一。
     */
    private static class SingletonInstance {
            private static final GatewayFilterChainFactory INSTANCE = new GatewayFilterChainFactory();
    }

    public static GatewayFilterChainFactory getInstance() {
        return SingletonInstance.INSTANCE;
    }

    private GatewayFilterChainFactory() {
        /**
         * 读取配置文件，初始化过滤器链,SPI机制
         */

        ServiceLoader<Filter> serviceLoader = ServiceLoader.load(Filter.class);

        for (Filter filter : serviceLoader){
            FilterAspect annotation = filter.getClass().getAnnotation(FilterAspect.class);
            logger.info("加载过滤器成功：{}, {}, {}, {}", filter.getClass(),
                    annotation.id(), annotation.name(), annotation.order());
            if (annotation != null) {
                // 添加到过滤集合
                String filterId = annotation.id();
                if (StringUtils.isEmpty(filterId)) {
                    filterId = filter.getClass().getName();
                }
                processorFilterIdMap.putIfAbsent(filterId, filter);
            }
        }

    }

    @Override
    public GatewayFilterChain buildFilterChain(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request, HttpStatement httpstatement) throws Exception {

        // 完成初始化
        GatewayFilterChain chain = new GatewayFilterChain();
        ArrayList<Filter> filters = new ArrayList<>();

        // TODO：还未完成解耦工作，目前还是和 FilterConst 中的过滤器耦合在一起

        // 添加灰度发布过滤器
        filters.add(getFilterInfo(FilterConst.GRAY_FILTER_ID));
        // 添加监控过滤器
        filters.add(getFilterInfo(FilterConst.MONITOR_FILTER_ID));
        filters.add(getFilterInfo(FilterConst.MONITOR_END_FILTER_ID));

        FilterRule rule = new FilterRule();

        if (rule != null) {
            Set<FilterRule.FilterConfig> filterConfigs = rule.getFilterConfigs();
            Iterator iterator = filterConfigs.iterator();
            FilterRule.FilterConfig filterConfig;
            while (iterator.hasNext()) {
                filterConfig = (FilterRule.FilterConfig) iterator.next();
                if (filterConfig == null) {
                    continue;
                }
                String filterId = filterConfig.getId();
                if (StringUtils.isNotEmpty(filterId) && getFilterInfo(filterId) != null) {
                    // 根据 filterId 获取对应的 filter，并添加
                    Filter filter = getFilterInfo(filterId);
                    filters.add(filter);
                }
            }
        }
        // TODO: 最后一个过滤器：添加路由过滤器
        // filters.add(new RouterFilter());
        // 排序
        filters.sort(Comparator.comparingInt(Filter::getOrder));
        // 添加到链中
        chain.addFilterList(filters);
        return chain;

    }

    @Override
    public <T> T getFilterInfo(String filterId) throws Exception {
        return null;
    }
}
