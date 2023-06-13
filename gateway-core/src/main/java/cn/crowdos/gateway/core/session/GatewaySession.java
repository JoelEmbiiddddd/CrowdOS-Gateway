package cn.crowdos.gateway.core.session;


import cn.crowdos.gateway.core.bind.IGenericReference;

import java.util.Map;

/**
 * @File : GatewaySession.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 用户处理网关 HTTP 请求
 * @Email : iHuanglixin@outlook.com
 */

public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();
}
