package cn.crowdos.gateway.center.application;

import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;

import java.util.Map;


/**
 * @File : IMessageService.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public interface IMessageService {

    Map<String, String> queryRedisConfig();

    void pushMessage(String gatewayId, Object message);
}
