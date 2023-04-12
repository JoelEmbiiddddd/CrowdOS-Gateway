package cn.crowdos.gateway.sdk.annotation;

import java.lang.annotation.*;

/**
 * @File : ApiProducerMethod.py
 * @Author : LiXin Huang, NWPU
 * @Date : 2023/4/12
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiProducerMethod {

    /** 方法名称 */
    String methodName() default "";

    /** 访问路径；/wg/activity/sayHi */
    String uri() default "";

    /** 接口类型；GET、POST、PUT、DELETE */
    String httpCommandType() default "GET";

    /** 是否认证；true = 1是、false = 0否 */
    int auth() default 0;

}