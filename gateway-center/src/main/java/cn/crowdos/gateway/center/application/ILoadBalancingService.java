package cn.crowdos.gateway.center.application;
import cn.crowdos.gateway.center.domain.docker.model.aggregates.NginxConfig;

import java.io.IOException;
/**
 * @File : ILoadBalancingService.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public interface ILoadBalancingService {
    void updateNginxConfig(NginxConfig nginxConfig) throws Exception;
}
