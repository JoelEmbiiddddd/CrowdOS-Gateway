package cn.crowdos.gateway.core.bind;


import cn.crowdos.gateway.core.executor.result.SessionResult;

import java.util.Map;

/**
 * @File : IGenericReference.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public interface IGenericReference {

    SessionResult $invoke(Map<String, Object> params);

}
