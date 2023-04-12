package cn.crowdos.gateway.engine;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @File : Application.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 启动服务
 * @Email : iHuanglixin@outlook.com
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Configurable
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}