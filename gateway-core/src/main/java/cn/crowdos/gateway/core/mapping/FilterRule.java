package cn.crowdos.gateway.core.mapping;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @File : FilterRule.java
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class FilterRule implements Comparable<FilterRule>, Serializable{

    /**
     * 规则ID，全局唯一
     */
    private String id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 规则排序，对应场景：一个路径对应多条规则，然后只执行一条规则的情况
     */
    private Integer order;

    /**
     * 后端服务id
     */
    private String serviceId;

    /**
     * 请求前缀
     */
    private String prefix;

    /**
     * 路径集合
     */
    private List<String> paths;

    private Set<FilterConfig> filterConfigs =new HashSet<>();

    private RetryConfig retryConfig = new RetryConfig();



    /**
     * 限流规则配置
     */
    private Set<FlowCtlConfig> flowCtlConfigs =new HashSet<>();


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Set<FilterConfig> getFilterConfigs() {
        return filterConfigs;
    }

    public void setFilterConfigs(Set<FilterConfig> filterConfigs) {
        this.filterConfigs = filterConfigs;
    }

    public Set<FlowCtlConfig> getFlowCtlConfigs() {
        return flowCtlConfigs;
    }

    public void setFlowCtlConfigs(Set<FlowCtlConfig> flowCtlConfigs) {
        this.flowCtlConfigs = flowCtlConfigs;
    }

    public FilterRule(){
        super();
    }

    public FilterRule(String id, String name, String protocol,
                Integer order, String serviceId, String prefix,
                List<String> paths, Set<FilterConfig> filterConfigs) {
        this.id = id;
        this.name = name;
        this.protocol = protocol;
        this.order = order;
        this.serviceId = serviceId;
        this.prefix = prefix;
        this.paths = paths;
        this.filterConfigs = filterConfigs;
    }

    /**
     * --------------------------------------  过滤器配置内部类  -----------------
     */
    public static class FilterConfig {

        /**
         * 过滤器唯一ID
         */
        private String id;
        /**
         * 过滤器规则描述，{"timeOut":500,"balance":random}
         */
        private String config;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConfig() {
            return config;
        }

        public void setConfig(String config) {
            this.config = config;
        }

        @Override
        public  boolean equals(Object o){
            if (this == o) {
                return  true;
            }

            if ((o== null) || getClass() != o.getClass()) {
                return false;
            }

            FilterConfig that =(FilterConfig)o;
            return id.equals(that.id);
        }

        @Override
        public  int hashCode (){
            return Objects.hash(id);
        }
    }

    /**
     * 向规则里面添加过滤器
     * @param filterConfig
     * @return
     */
    public boolean addFilterConfig (FilterConfig filterConfig){
        return filterConfigs.add(filterConfig);
    }

    /**
     * 通过一个指定的FilterID获取FilterConfig
     * @param id
     * @return
     */
    public FilterConfig getFilterConfig (String id) {
        for (FilterConfig config:filterConfigs) {
            if (config.getId().equalsIgnoreCase(id)) {
                return config;
            }
        }
        return null;
    }

    /**
     * 根据filterID判断当前Rule是否存在
     * @return
     */
    public boolean hashId() {
        for(FilterConfig config:filterConfigs){
            if(config.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }


    @Override
    public int compareTo(FilterRule o) {
        int orderCompare = Integer.compare(getOrder(),o.getOrder());
        if(orderCompare == 0){
            return getId().compareTo(o.getId());
        }
        return orderCompare;
    }

    @Override
    public  boolean equals(Object o){
        if (this == o) return  true;

        if((o== null) || getClass() != o.getClass()){
            return false;
        }

        FilterConfig that =(FilterConfig)o;
        return id.equals(that.id);
    }

    @Override
    public  int hashCode(){
        return Objects.hash(id);
    }

    /**
     * --------------------------------------  重试机制内部类  -----------------
     */
    public static class RetryConfig {
        /**
         * 重试次数
         */
        private int times;

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }

    public RetryConfig getRetryConfig() {
        return retryConfig;
    }

    public void setRetryConfig(RetryConfig retryConfig) {
        this.retryConfig = retryConfig;
    }

    /**
     * --------------------------------------  限流流控配置内部类  -----------------
     */
    public static class FlowCtlConfig {
        /**
         * 限流类型：可对 path，ip 或服务
         */
        private String type;

        /**
         * 限流对象的值
         */
        private String value;

        /**
         * 限流模式：单机 or 分布式
         */
        private String model;

        /**
         * 限流规则
         */
        private String config;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getConfig() {
            return config;
        }

        public void setConfig(String config) {
            this.config = config;
        }
    }

    /**
     * --------------------------------------  熔断限流配置内部类  -----------------
     */


}
