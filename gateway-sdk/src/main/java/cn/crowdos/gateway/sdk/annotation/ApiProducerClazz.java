package cn.crowdos.gateway.sdk.annotation;

import java.lang.annotation.*;

/**
 * @File : ApiProducerClazz.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关API生产者自定义注解
 * @Email : iHuanglixin@outlook.com
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ApiProducerClazz {

    /** 接口名称 */
    String interfaceName() default "";

    /** 接口版本 */
    String interfaceVersion() default "";
}

