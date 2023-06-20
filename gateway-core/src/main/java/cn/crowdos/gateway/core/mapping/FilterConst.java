package cn.crowdos.gateway.core.mapping;

/**
 * @File : FilterConst.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 负载均衡过滤器常量
 * @Email : iHuanglixin@outlook.com
 */

public interface FilterConst {

    /**
     * ---------------- 负载均衡过滤器 --------------------------
     */
    String LOAD_BALANCE_FILTER_ID = "load_balancer_filter";

    String LOAD_BALANCE_FILTER_NAME = "load_balancer_filter";

    int LOAD_BALANCE_FILTER_ORDER = 100;

    String LOAD_BALANCE_KEY = "load_balancer";

    String LOAD_BALANCE_STRATEGY_RANDOM = "Random";

    String LOAD_BALANCE_STRATEGY_ROUND_ROBIN = "RoundRobin";

    /**
     * ---------------- 路由过滤器 --------------------------
     */
    String ROUTER_FILTER_ID = "router_filter";

    String ROUTER_FILTER_NAME = "router_filter";

    int ROUTER_FILTER_ORDER = Integer.MAX_VALUE - 1;

    /**
     * ---------------- 用户鉴权过滤器 --------------------------
     */
    String USER_AUTH_FILTER_ID = "user_auth_filter";

    String USER_AUTH_FILTER_NAME = "user_auth_filter";

    int USER_AUTH_FILTER_ORDER = 1;

    /**
     * ---------------- 灰度流量过滤器 --------------------------
     */
    String GRAY_FILTER_ID = "gray_filter";

    String GRAY_FILTER_NAME = "gray_filter";

    int GRAY_FILTER_ORDER = 0;

    /**
     * ---------------- 指标监控过滤器 --------------------------
     */
    String MONITOR_FILTER_ID = "monitor_filter";

    String MONITOR_FILTER_NAME = "monitor_filter";

    int MONITOR_FILTER_ORDER = -1;

    String MONITOR_END_FILTER_ID = "monitor_end_filter";

    String MONITOR_END_FILTER_NAME = "monitor_end_filter";

    int MONITOR_END_FILTER_ORDER = Integer.MAX_VALUE;

    /**
     * ---------------- 限流过滤器 --------------------------
     */
    String FLOW_CTL_FILTER_ID = "flow_ctl_filter";
    String FLOW_CTL_FILTER_NAME = "flow_ctl_filter";
    int FLOW_CTL_FILTER_ORDER = 50;

    String FLOW_CTL_TYPE_PATH = "path";
    String FLOW_CTL_TYPE_SERVICE = "service";

    String FLOW_CTL_LIMIT_DURATION = "duration"; //以秒为单位
    String FLOW_CTL_LIMIT_PERMITS = "permits"; //允许请求的次数

    String FLOW_CTL_MODEL_DISTRIBUTED = "distributed";
    String FLOW_CTL_MODEL_SINGLETON = "Singleton";

}
