package cn.crowdos.gateway.core.filter.router;

import cn.crowdos.gateway.core.filter.FilterAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/**
 * @File : RouterFilter.java
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */
@Slf4j
@FilterAspect(id = ROUTER_FILTER_ID, name = ROUTER_FILTER_NAME, order = ROUTER_FILTER_ORDER)
public class RouterFilter implements Filter {

    private static Logger accessLog = LoggerFactory.getLogger("accessLog");
    /**
     * 路由请求转发
     * @param gatewayContext
     * @throws Exception
     */
    @Override
    public void doFilter(GatewayContext gatewayContext) throws Exception {

        // Optional 可以对可能缺失的值进行建模，而不是直接将 null 赋值给变量。
        Optional<Rule.HystrixConfig> hystrixConfig = getHystrixConfig(gatewayContext);
        if (hystrixConfig.isPresent()) {
            // 熔断异常发送路由
            routeWithHystrix(gatewayContext, hystrixConfig);
        } else {
            // 无熔断异常发送请求
            route(gatewayContext, hystrixConfig);
        }
    }

    private void routeWithHystrix(GatewayContext gatewayContext, Optional<Rule.HystrixConfig> hystrixConfig) {
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey
                        .Factory
                        .asKey(gatewayContext.getUniqueId()))
                .andCommandKey(HystrixCommandKey.Factory
                        .asKey(gatewayContext.getRequest().getPath()))
                // 定义线程池大小
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(hystrixConfig.get().getThreadCoreSize()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 定义线程池隔离方式
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        // 定义超时时间
                        .withExecutionTimeoutInMilliseconds(hystrixConfig.get().getTimeoutInMilliseconds())
                        .withExecutionIsolationThreadInterruptOnTimeout(true)
                        .withExecutionTimeoutEnabled(true));
        new HystrixCommand<Object> (setter) {
            @Override
            protected Object run() throws Exception {
                route(gatewayContext, hystrixConfig).get();
                return null;
            }

            @Override
            protected Object getFallback() {
                gatewayContext.setResponse(hystrixConfig);
                gatewayContext.setWritten();
                return null;
            }
        }.execute();

    }

    private CompletableFuture<Response> route (GatewayContext gatewayContext, Optional<Rule.HystrixConfig> hystrixConfig) {
        Request request = gatewayContext.getRequest().build();
        // 发起请求
        CompletableFuture<Response> future = AsyncHttpHelper.getInstance().executeRequest(request);

        boolean whenComplete = ConfigLoader.getConfig().isWhenComplete();
        if (whenComplete) {
            // 单异步模式
            future.whenComplete((response, throwable) -> {
                complete(request, response, throwable, gatewayContext, hystrixConfig);
            });
        } else {
            // 双异步模式
            future.whenCompleteAsync((response, throwable) -> {
                complete(request, response, throwable, gatewayContext, hystrixConfig);
            });
        }
        return future;
    }


    private static Optional<Rule.HystrixConfig> getHystrixConfig (GatewayContext gatewayContext) {
        Rule rule = gatewayContext.getRule();
        Optional<Rule.HystrixConfig> hystrixConfig = rule.getHystrixConfigs().stream()
                .filter(c -> StringUtils.equals(c.getPath(), gatewayContext.getRequest().getPath())).findFirst();
        return hystrixConfig;
    }

    private void complete(Request request,
                          Response response,
                          Throwable throwable,
                          GatewayContext gatewayContext,
                          Optional<Rule.HystrixConfig> hystrixConfig) {
        // 释放请求资源
        gatewayContext.releaseRequest();

        Rule rule = gatewayContext.getRule();
        // 当前重试次数
        int currentRetryTimes = gatewayContext.getCurrentRetryTimes();
        // 配置的重试次数
        int confRetryTimes = rule.getRetryConfig().getTimes();
        // 重试条件
        if ((throwable instanceof TimeoutException
                || throwable instanceof IOException)
                && currentRetryTimes <= confRetryTimes && !hystrixConfig.isPresent()) {
            doRetry(gatewayContext, currentRetryTimes);
            return;
        }

        try {
            // 判断有没有异常
            if (Objects.nonNull(throwable)) {
                String url = request.getUrl();
                if (throwable instanceof TimeoutException) {
                    log.warn("complete time out {}", url);
                    gatewayContext.setThrowable(new ResponseException(ResponseCode.REQUEST_TIMEOUT));
                } else {
                    gatewayContext.setThrowable(new ConnectException(throwable,
                            gatewayContext.getUniqueId(),
                            url, ResponseCode.HTTP_RESPONSE_ERROR));
                }
            } else {
                // 没有异常，正常响应结果
                gatewayContext.setResponse(GatewayResponse.buildGatewayResponse(response));
            }
        } catch (Throwable t) {
            gatewayContext.setThrowable(new ResponseException(ResponseCode.INTERNAL_ERROR));
            log.error("complete error", t);
        } finally {
            // 改变 context 状态
            gatewayContext.setWritten();
            // 写回数据
            ResponseHelper.writeResponse(gatewayContext);
            // 记录访问日志
            accessLog.info("{} {} {} {} {} {} {}",
                    System.currentTimeMillis() - gatewayContext.getRequest().getBeginTime(),
                    gatewayContext.getRequest().getClientIp(),
                    gatewayContext.getRequest().getUniqueId(),
                    gatewayContext.getRequest().getMethod(),
                    gatewayContext.getRequest().getPath(),
                    gatewayContext.getResponse().getHttpResponseStatus().code(),
                    gatewayContext.getResponse().getFutureResponse().getResponseBodyAsBytes().length);
        }
    }

    /**
     * 重试
     * @param gatewayContext
     * @param currentRetryTimes
     */
    private void doRetry(GatewayContext gatewayContext, int currentRetryTimes) {
        // 重试次数 + 1
        System.out.println("当前重试次数为：" + currentRetryTimes);
        gatewayContext.setCurrentRetryTimes(currentRetryTimes + 1);
        try {
            doFilter(gatewayContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getOrder() {
        return ROUTER_FILTER_ORDER;
    }
}
