package cn.crowdos.gateway.core.datasource.unpooled;

import cn.crowdos.gateway.core.datasource.DataSource;
import cn.crowdos.gateway.core.datasource.DataSourceFactory;
import cn.crowdos.gateway.core.datasource.DataSourceType;
import cn.crowdos.gateway.core.session.Configuration;

/**
 * @File : UnpooledDataSourceFactory.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        this.dataSource.setDataSourceType(DataSourceType.Dubbo);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
