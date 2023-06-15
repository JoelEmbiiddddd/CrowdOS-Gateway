package cn.crowdos.gateway.core.filter.loadbalance;

/**
 * @File : IGatewayLoadBalanceRule.java
 * @Author : LiXin Huang, NWPU
 * @Date : 2023/6/15
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public interface IGatewayLoadBalanceRule {

    /**
     * 通过上下文参数 选择 对应的服务实例
     * @param context
     * @return
     */
    ServiceInstance choose (GatewayContext context);

    /**
     * 根据服务 id 选择 对应的服务实例
     * @param serviceId
     * @return
     */
    ServiceInstance choose (String serviceId, boolean gray);
}
