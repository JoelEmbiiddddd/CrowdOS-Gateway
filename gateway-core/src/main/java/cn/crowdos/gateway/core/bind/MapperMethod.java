package cn.crowdos.gateway.core.bind;

import cn.crowdos.gateway.core.mapping.HttpCommandType;
import cn.crowdos.gateway.core.session.Configuration;
import cn.crowdos.gateway.core.session.GatewaySession;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @File : MapperMethod.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class MapperMethod {

    private String methodName;
    private final HttpCommandType command;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.methodName = configuration.getHttpStatement(uri).getMethodName();
        this.command = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Map<String, Object> params) {
        Object result = null;
        switch (command) {
            case GET:
                result = session.get(methodName, params);
                break;
            case POST:
                result = session.post(methodName, params);
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command);
        }
        return result;
    }

}
