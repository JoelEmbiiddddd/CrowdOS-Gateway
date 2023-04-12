package cn.crowdos.gateway.center.infrastructure.dao;


import cn.crowdos.gateway.center.domain.operation.model.vo.GatewayServerDetaiDatalVO;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.po.GatewayServerDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @File : IGatewayServerDetailDao.py
 * @Author : LiXin Huang, NWPU
 * @Date : 2023/4/10
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */
@Mapper
public interface IGatewayServerDetailDao {

    void insert(GatewayServerDetail gatewayServerDetail);

    GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

    boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

    List<GatewayServerDetail> queryGatewayServerDetailList();

    List<GatewayServerDetail> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

    int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

}
