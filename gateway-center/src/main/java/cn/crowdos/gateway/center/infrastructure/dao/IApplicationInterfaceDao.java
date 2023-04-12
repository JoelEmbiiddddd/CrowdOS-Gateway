package cn.crowdos.gateway.center.infrastructure.dao;

import cn.crowdos.gateway.center.domain.operation.model.vo.ApplicationInterfaceDataVO;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.po.ApplicationInterface;
import cn.crowdos.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @File : IApplicationInterfaceDao.py
 * @Author : LiXin Huang, NWPU
 * @Desc :  应用接口
 * @Email : iHuanglixin@outlook.com
 */
@Mapper
public interface IApplicationInterfaceDao {

    void insert(ApplicationInterface applicationInterface);

    List<ApplicationInterface> queryApplicationInterfaceList(String systemId);

    List<ApplicationInterface> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);

    int queryApplicationInterfaceListCountByPage(OperationRequest<ApplicationInterfaceDataVO> request);

    boolean isExistByInterfaceId(ApplicationInterface applicationInterface);
}
