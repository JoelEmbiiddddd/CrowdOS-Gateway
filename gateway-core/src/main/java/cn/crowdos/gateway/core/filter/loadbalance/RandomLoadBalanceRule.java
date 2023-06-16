package cn.crowdos.gateway.core.filter.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @File : RandomLoadBalanceRule.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 负载均衡-随机
 * @Email : iHuanglixin@outlook.com
 */

public class RandomLoadBalanceRule implements IGatewayLoadBalanceRule{

    private final String serviceId;

    private Set<ServiceInstance> serviceInstanceSet;

    public RandomLoadBalanceRule(String serviceId) {
        this.serviceId = serviceId;
    }

    private static ConcurrentHashMap<String, RandomLoadBalanceRule> serviceMap = new ConcurrentHashMap<>();

    public static RandomLoadBalanceRule getInstance(String serviceId) {
        // 先尝试从 serviceMap 中拿
        RandomLoadBalanceRule loadBalanceRule = serviceMap.get(serviceId);
        if (loadBalanceRule == null) {
            loadBalanceRule = new RandomLoadBalanceRule(serviceId);
            serviceMap.put(serviceId, loadBalanceRule);
        }
        return loadBalanceRule;
    }


    @Override
    public ServiceInstance choose(GatewayContext context) {
        String serviceId = context.getUniqueId();
        return choose(serviceId, context.isGray());
    }

    @Override
    public ServiceInstance choose(String serviceId, boolean gray) {
        Set<ServiceInstance> serviceInstanceSet =
                DynamicConfigManager.getInstance().getServiceInstanceByUniqueId(serviceId, gray);
        if (serviceInstanceSet.isEmpty()) {
            log.warn("No instance available for: {}", serviceId);
            throw new NotFoundException(ResponseCode.SERVICE_INSTANCE_NOT_FOUND);
        }
        List<ServiceInstance> instances = new ArrayList<>(serviceInstanceSet);
        int index = ThreadLocalRandom.current().nextInt(instances.size());
        return instances.get(index);
    }
}
