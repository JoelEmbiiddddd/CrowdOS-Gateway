package cn.crowdos.gateway.core.bind;


import cn.crowdos.gateway.core.executor.result.SessionResult;

import java.util.Map;

/**
 * @File : IGenericReference.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 定义了一个通用的接口，用于实现动态代理。 即 在网络请求的时候可以进行硬编码，使用$invoke方法进行调用
 * @Email : iHuanglixin@outlook.com
 */

public interface IGenericReference {

    SessionResult $invoke(Map<String, Object> params);

}
