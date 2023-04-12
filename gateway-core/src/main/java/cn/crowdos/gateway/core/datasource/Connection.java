package cn.crowdos.gateway.core.datasource;

/**
 * @File : Connection.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 连接接口
 * @Email : iHuanglixin@outlook.com
 */

public interface Connection {

    Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
