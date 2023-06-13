package cn.crowdos.gateway.core.authorization;

/**
 * @File : IAuth.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 认证服务接口
 * @Email : iHuanglixin@outlook.com
 */

public interface IAuth {

    boolean validate(String id, String token);

}
