package cn.crowdos.gateway.center.application;
import cn.crowdos.gateway.center.domain.operation.model.vo.*;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.common.OperationResult;
/**
 * @File : IDataOperationManageService.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关运营数据管理
 * @Email : iHuanglixin@outlook.com
 */

public interface IDataOperationManageService {
    OperationResult<GatewayServerDataVO> queryGatewayServer(OperationRequest<String> request);

    OperationResult<ApplicationSystemDataVO> queryApplicationSystem(OperationRequest<ApplicationSystemDataVO> request);

    OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(OperationRequest<ApplicationInterfaceDataVO> request);

    OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    OperationResult<GatewayServerDetaiDatalVO> queryGatewayServerDetail(OperationRequest<GatewayServerDetaiDatalVO> request);

    OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(OperationRequest<GatewayDistributionDataVO> request);
}
