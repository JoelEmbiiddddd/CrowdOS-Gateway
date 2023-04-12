package cn.crowdos.gateway.core.datasource;

/**
 * @File : DataSource.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 数据源接口，RPC、HTTP 都当做连接的数据资源使用
 * @Email : iHuanglixin@outlook.com
 */

public interface DataSource {
    Connection getConnection();
}
