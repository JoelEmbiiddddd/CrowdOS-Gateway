package cn.crowdos.gateway.center.application;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationSystemVO;
/**
 * @File : IRegisterManageService.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public interface IRegisterManageService {
    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);
}
