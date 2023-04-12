package cn.crowdos.gateway.sdk;

/**
 * @File : GatewayException.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关异常
 * @Email : iHuanglixin@outlook.com
 */

public class GatewayException extends RuntimeException{
    public GatewayException(String msg) {
        super(msg);
    }

    public GatewayException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
