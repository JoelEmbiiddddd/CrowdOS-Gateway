package cn.crowdos.gateway.core.session;

/**
 * @File : GatewaySessionFactory.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关会话工厂接口
 * @Email : iHuanglixin@outlook.com
 */

public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);

}
