package cn.crowdos.gateway.center.domain.register.repository;


import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 * @File : IRegisterManageRepository.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 接口注册仓储服务
 * @Email : iHuanglixin@outlook.com
 */

public interface IRegisterManageRepository {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

    boolean isExistBySystemId(String systemId);


}
