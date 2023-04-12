package cn.crowdos.gateway.sdk.config;


import cn.crowdos.gateway.sdk.application.GatewaySDKApplication;
import cn.crowdos.gateway.sdk.domain.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @File : GatewaySDKAutoConfig.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关SDK配置服务
 * @Email : iHuanglixin@outlook.com
 */


@Configuration
@EnableConfigurationProperties(GatewaySDKServiceProperties.class)
@ConditionalOnProperty(
        prefix = "api-gateway-sdk",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class GatewaySDKAutoConfig {

    @Bean
    public GatewayCenterService gatewayCenterService() {
        return new GatewayCenterService();
    }

    @Bean
    public GatewaySDKApplication gatewaySDKApplication(GatewaySDKServiceProperties properties, GatewayCenterService gatewayCenterService) {
        return new GatewaySDKApplication(properties, gatewayCenterService);
    }
}
