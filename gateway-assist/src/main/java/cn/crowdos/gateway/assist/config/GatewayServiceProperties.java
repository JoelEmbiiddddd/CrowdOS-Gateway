package cn.crowdos.gateway.assist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @File : GatewayServiceProperties.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关服务注册配置
 * @Email : iHuanglixin@outlook.com
 */

// 在 SpringBoot Starter 的组件开发中，需要使用注解 @ConfigurationProperties("api-gateway") 标记出作为配置的文件类。
// 在类中添加属性信息，这些属性信息就是最后的配置到 yml 中的配置属性。
@ConfigurationProperties("api-gateway")
public class GatewayServiceProperties {

    /** 注册中心地址 */
    private String address;
    /** 分组ID */
    private String groupId;
    /** 网关ID */
    private String gatewayId;
    /** 网关名称 */
    private String gatewayName;
    /** 网关地址 */
    private String gatewayAddress;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

}
