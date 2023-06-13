package cn.crowdos.gateway.core.filter;

/**
 * @File : HttpCommandType.java
 * @Author : LiXin Huang, NWPU
 * @Desc : 注解类
 * @Email : iHuanglixin@outlook.com
 */

public @interface FilterAspect {

    /**
     * 过滤器ID
     * @return
     */
    String id();

    /**
     * 过滤器名称
     * @return
     */
    String name() default "";

    /**
     * 过滤器排序
     * @return
     */
    int order() default 0;
}
