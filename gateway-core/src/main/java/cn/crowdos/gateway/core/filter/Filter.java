package cn.crowdos.gateway.core.filter;
import cn.crowdos.gateway.core.filter.GatewayContext;
/**
 * @File : Filter.java
 * @Author : LiXin Huang, NWPU
 * @Date : 2023/6/14
 * @Desc : 过滤器接口
 * @Email : iHuanglixin@outlook.com
 */

public interface Filter {

    /**
     * 执行过滤器
     * @param ctx
     * @throws Exception
     */
    void doFilter(GatewayContext ctx) throws Exception;

    /**
     * 通过注解拿到排序
     * @return
     */
    default int getOrder() {
        FilterAspect annotation = this.getClass().getAnnotation(FilterAspect.class);
        if (annotation != null) {
            return annotation.order();
        }
        return Integer.MAX_VALUE;
    }
}
