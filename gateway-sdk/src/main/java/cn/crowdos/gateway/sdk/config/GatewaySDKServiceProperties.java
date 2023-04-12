package cn.crowdos.gateway.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @File : GatewaySDKServiceProperties.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 应用配置
 * @Email : iHuanglixin@outlook.com
 */

@ConfigurationProperties(prefix = "api-gateway-sdk")
public class GatewaySDKServiceProperties {

    /** 网关注册中心地址 */
    private String address;
    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** RPC注册中心；zookeeper://127.0.0.1:2181*/
    private String systemRegistry;
    /** 程序是否启用 */
    private boolean enabled = true;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
