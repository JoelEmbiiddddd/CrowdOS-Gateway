package cn.crowdos.gateway.center.domain.register.service;

import cn.crowdos.gateway.center.application.IRegisterManageService;
import cn.crowdos.gateway.center.domain.message.Publisher;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import cn.crowdos.gateway.center.domain.register.repository.IRegisterManageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @File : IRegisterManageServiceImpl.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 接口注册服务
 * @Email : iHuanglixin@outlook.com
 */
@Service
public class IRegisterManageServiceImpl implements IRegisterManageService {

    @Resource
    private IRegisterManageRepository registerManageRepository;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        registerManageRepository.registerApplication(applicationSystemVO);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        registerManageRepository.registerApplicationInterface(applicationInterfaceVO);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        registerManageRepository.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
    }

}
