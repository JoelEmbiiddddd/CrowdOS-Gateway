package cn.crowdos.gateway.center.infrastructure.dao;

import cn.crowdos.gateway.center.domain.operation.model.vo.ApplicationInterfaceMethodDataVO;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @File : IApplicationInterfaceMethodDao.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 应用接口方法
 * @Email : iHuanglixin@outlook.com
 */
@Mapper
public interface IApplicationInterfaceMethodDao {

    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodList(ApplicationInterfaceMethod req);

    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    int queryApplicationInterfaceMethodListCountByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    boolean isExistByInterfaceMethodId(ApplicationInterfaceMethod applicationInterfaceMethod);

}
