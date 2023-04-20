package cn.crowdos.gateway.assist.common;

/**
 * @File : Result.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 统一返回对象中，Code码、Info描述
 * @Email : iHuanglixin@outlook.com
 */

public class Result<T> {

    private String code;
    private String info;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
