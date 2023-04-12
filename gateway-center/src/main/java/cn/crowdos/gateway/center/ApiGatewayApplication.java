package cn.crowdos.gateway.center;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @File : ApiGatewayApplication.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关注册中心启动服务
 * @Email : iHuanglixin@outlook.com
 */

@SpringBootApplication
@Configurable
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
