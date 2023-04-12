package cn.crowdos.gateway.core.executor;


import cn.crowdos.gateway.core.datasource.Connection;
import cn.crowdos.gateway.core.session.Configuration;

/**
 * @File : SimpleExecutor.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 简单执行器
 * @Email : iHuanglixin@outlook.com
 */

public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Connection connection) {
        super(configuration, connection);
    }

    @Override
    protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {
        /*
         * 调用服务
         * 封装参数 PS：为什么这样构建参数，可以参考测试案例；cn.crowdos.gateway.test.RPCTest
         * 01(允许)：java.lang.String
         * 02(允许)：cn.crowdos.gateway.rpc.dto.XReq
         * 03(拒绝)：java.lang.String, cn.crowdos.gateway.rpc.dto.XReq —— 不提供多参数方法的处理
         * */
        return connection.execute(methodName, parameterTypes, new String[]{"ignore"}, args);
    }
}
