package cn.crowdos.gateway.core.filter.loadbalance;

import com.alibaba.fastjson.JSON;
import cn.crowdos.gateway.core.filter.FilterAspect;
import cn.crowdos.gateway.core.filter.Filter;
import cn.crowdos.gateway.core.filter.GatewayContext;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @File : LoadBalanceFilter.java
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

@FilterAspect(id = LOAD_BALANCE_FILTER_ID, name = LOAD_BALANCE_FILTER_NAME, order = LOAD_BALANCE_FILTER_ORDER)
public class LoadBalanceFilter implements Filter {
    @Override
    public void doFilter(GatewayContext ctx) throws Exception {
        String serviceId = ctx.getUniqueId();
        IGatewayLoadBalanceRule gatewayLoadBalanceRule = getLoadBalanceRule(ctx);
        // 根据负载均衡算法选择一个服务实例
        ServiceInstance serviceInstance = gatewayLoadBalanceRule.choose(serviceId, ctx.isGray());
        GatewayRequest request = ctx.getRequest();
        if (serviceId != null && request != null) {
            String host = serviceInstance.getIp() + COLON_SEPARATOR + serviceInstance.getPort();
            request.setModifyHost(host);
        } else {
            log.warn("No instance available for : {}", serviceId);
            throw new NotFoundException(ResponseCode.SERVICE_DEFINITION_NOT_FOUND);
        }
    }

    /**
     * 根据上下文获取对应的负载均衡算法
     * @param ctx
     * @return
     */
    private IGatewayLoadBalanceRule getLoadBalanceRule(GatewayContext ctx) {
        IGatewayLoadBalanceRule loadBalanceRule = null;
        Rule configRule = ctx.getRule();
        if (configRule != null) {
            Set<Rule.FilterConfig> filterConfigs = configRule.getFilterConfigs();
            Iterator iterator = filterConfigs.iterator();
            Rule.FilterConfig filterConfig;
            while (iterator.hasNext()) {
                filterConfig = (Rule.FilterConfig) iterator.next();
                if (filterConfig == null) {
                    continue;
                }
                String filterId = filterConfig.getId();
                if (LOAD_BALANCE_FILTER_ID.equals(filterId)) {
                    // 如果匹配上，直接拿
                    String config = filterConfig.getConfig();
                    // 默认是随机
                    String strategy = LOAD_BALANCE_STRATEGY_RANDOM;
                    if (StringUtils.isNotEmpty(config)) {
                        Map<String, String> mapTypeMap = JSON.parseObject(config, Map.class);
                        strategy = mapTypeMap.get(LOAD_BALANCE_KEY);
                    }
                    switch (strategy) {
                        case LOAD_BALANCE_STRATEGY_RANDOM:
                            loadBalanceRule = RandomLoadBalanceRule.getInstance(configRule.getServiceId());
                            break;
                        case LOAD_BALANCE_STRATEGY_ROUND_ROBIN:
                            loadBalanceRule = RoundRobinLoadBalanceRule.getInstance(configRule.getServiceId());
                            break;
                        default:
                            log.warn("No loadBalance strategy for service: {}", strategy);
                            loadBalanceRule = RandomLoadBalanceRule.getInstance(configRule.getServiceId());
                            break;
                    }
                }
            }
        }
        return loadBalanceRule;
    }
}
