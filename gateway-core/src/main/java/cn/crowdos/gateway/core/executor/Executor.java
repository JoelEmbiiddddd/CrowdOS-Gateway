package cn.crowdos.gateway.core.executor;

import cn.crowdos.gateway.core.executor.result.SessionResult;
import cn.crowdos.gateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * @File : Executor.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 引入执行器，封装服务调用，在session中不应该直接接触到数据源，而应该是执行器，避免更多的信息暴露
 * @Email : iHuanglixin@outlook.com
 */

public interface Executor {

    // 返回的应该是一个网关标准格式的信息
    SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
