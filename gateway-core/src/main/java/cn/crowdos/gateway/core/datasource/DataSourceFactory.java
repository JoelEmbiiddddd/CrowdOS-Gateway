package cn.crowdos.gateway.core.datasource;

import cn.crowdos.gateway.core.session.Configuration;
/**
 * @File : DataSourceFactory.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();
}
