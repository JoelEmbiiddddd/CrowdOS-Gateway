package cn.crowdos.gateway.center.infrastructure.dao;


import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.po.GatewayServer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @File : IGatewayServerDao.py
 * @Author : LiXin Huang, NWPU
 * @Date : 2023/4/10
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */
@Mapper
public interface IGatewayServerDao {

    List<GatewayServer> queryGatewayServerList();

    List<GatewayServer> queryGatewayServerListByPage(OperationRequest<String> request);

    int queryGatewayServerListCountByPage(OperationRequest<String> request);

}
