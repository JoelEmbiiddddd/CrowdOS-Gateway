package cn.crowdos.gateway.center.infrastructure.dao;


import cn.crowdos.gateway.center.domain.manage.model.vo.GatewayDistributionVO;
import cn.crowdos.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.po.GatewayDistribution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @File : IGatewayDistributionDao.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关分配
 * @Email : iHuanglixin@outlook.com
 */
@Mapper
public interface IGatewayDistributionDao {

    List<String> queryGatewayDistributionSystemIdList(String gatewayId);

    String queryGatewayDistribution(String systemId);

    List<GatewayDistribution> queryGatewayDistributionList();

    List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);

    void insert(GatewayDistributionVO gatewayDistribution);

}
