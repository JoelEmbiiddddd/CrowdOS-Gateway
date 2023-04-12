package cn.crowdos.gateway.center.infrastructure.dao;

import cn.crowdos.gateway.center.domain.operation.model.vo.ApplicationSystemDataVO;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @File : IApplicationSystemDao.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 应用系统
 * @Email : iHuanglixin@outlook.com
 */
@Mapper
public interface IApplicationSystemDao {

    void insert(ApplicationSystem applicationSystem);

    List<ApplicationSystem> queryApplicationSystemList(List<String> list);

    List<ApplicationSystem> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);

    int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request);

    String queryApplicationSystemName(String systemId);

    boolean isExistBySystemId(String systemId);

}
