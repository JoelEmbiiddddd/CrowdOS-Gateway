package cn.crowdos.gateway.core.socket.agreement;

/**
 * @File : GatewayResultMessage.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关结果封装
 * @Email : iHuanglixin@outlook.com
 */

public class GatewayResultMessage {
    private String node;
    private String code;
    private String info;
    private Object data;

    protected GatewayResultMessage(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResultMessage buildSuccess(Object data) {
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(), data);
    }

    public static GatewayResultMessage buildError(String code, String info) {
        return new GatewayResultMessage(code, info, null);
    }

    public String getNode() {
        return node;
    }

    public GatewayResultMessage setNode(String node) {
        this.node = node;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }
}
