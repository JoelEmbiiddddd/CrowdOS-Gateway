package cn.crowdos.gateway.center.interfaces;

import cn.crowdos.gateway.center.application.IDataOperationManageService;
import cn.crowdos.gateway.center.domain.operation.model.vo.*;
import cn.crowdos.gateway.center.infrastructure.common.OperationRequest;
import cn.crowdos.gateway.center.infrastructure.common.OperationResult;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;

/**
 * @File : DataOperationManage.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 网关数据操作管理
 * @Email : iHuanglixin@outlook.com
 */

@CrossOrigin
@RestController
@RequestMapping("/wg/admin/data")
public class DataOperationManage {


    // Redis缓存
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(DataOperationManage.class);

    @Resource
    private IDataOperationManageService dataOperationManageService;

    @GetMapping(value = "queryGatewayServer", produces = "application/json;charset=utf-8")
    public OperationResult<GatewayServerDataVO> queryGatewayServer(@RequestParam String groupId,
                                                                   @RequestParam String page,
                                                                    @RequestParam String limit) {

        String cacheKey = "queryGatewayServer:" + groupId + ":" + page + ":" + limit;

        OperationResult<GatewayServerDataVO> cachedData = getFromCache(cacheKey);

        if (cachedData != null) {
            logger.info("在queryGatewayServer的缓存中找到数据: {}", cacheKey);
            return cachedData;
        }

        try {
            logger.info("查询网关服务数据开始 groupId：{} page：{} limit：{}", groupId, page, limit);
            OperationRequest<String> req = new OperationRequest<>(page, limit);
            req.setData(groupId);
            OperationResult<GatewayServerDataVO> operationResult = dataOperationManageService.queryGatewayServer(req);

            // Save data to cache
            saveToCache(cacheKey, operationResult);

            logger.info("查询网关服务数据完成 operationResult：{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            logger.error("查询网关服务数据异常 groupId：{}", groupId, e);
            return new OperationResult<>(0, null);
        }
    }

    @GetMapping(value = "queryGatewayServerDetail", produces = "application/json;charset=utf-8")
    public OperationResult<GatewayServerDetaiDatalVO> queryGatewayServerDetail(@RequestParam String groupId,
                                                                               @RequestParam String gatewayId,
                                                                               @RequestParam String page,
                                                                               @RequestParam String limit) {

        String cacheKey = "queryGatewayServer:" + groupId + ":" + gatewayId + ":"+page + ":" + limit;
        OperationResult<GatewayServerDetaiDatalVO> cachedData = getFromCache(cacheKey);
        if (cachedData != null) {
            logger.info("在queryGatewayServer的缓存中找到数据: {}", cacheKey);
            return cachedData;
        }


        try {
            logger.info("查询网关服务详情数据开始 groupId：{} gatewayId：{} page：{} limit：{}", groupId, gatewayId, page, limit);
            OperationRequest<GatewayServerDetaiDatalVO> req = new OperationRequest<>(page, limit);
            req.setData(new GatewayServerDetaiDatalVO(groupId, gatewayId));
            OperationResult<GatewayServerDetaiDatalVO> operationResult = dataOperationManageService.queryGatewayServerDetail(req);
            // Save data to cache
            saveToCache(cacheKey, operationResult);
            logger.info("查询网关服务详情数据完成 operationResult：{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            logger.error("查询网关服务详情数据异常 groupId：{}", groupId, e);
            return new OperationResult<>(0, null);
        }
    }

    @GetMapping(value = "queryGatewayDistribution", produces = "application/json;charset=utf-8")
    public OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(@RequestParam String groupId,
                                                                               @RequestParam String gatewayId,
                                                                               @RequestParam String page,
                                                                               @RequestParam String limit) {
        String cacheKey = "queryGatewayServer:" + groupId + ":" + gatewayId + ":"+page + ":" + limit;
        OperationResult<GatewayDistributionDataVO> cachedData = getFromCache(cacheKey);
        if (cachedData != null) {
            logger.info("在queryGatewayServer的缓存中找到数据: {}", cacheKey);
            return cachedData;
        }


        try {
            logger.info("查询网关分配数据开始 groupId：{} gatewayId：{} page：{} limit：{}", groupId, gatewayId, page, limit);
            OperationRequest<GatewayDistributionDataVO> req = new OperationRequest<>(page, limit);
            req.setData(new GatewayDistributionDataVO(groupId, gatewayId));
            OperationResult<GatewayDistributionDataVO> operationResult = dataOperationManageService.queryGatewayDistribution(req);
            // Save data to cache
            saveToCache(cacheKey, operationResult);
            logger.info("查询网关分配数据完成 operationResult：{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            logger.error("查询网关分配数据异常 groupId：{}", groupId, e);
            return new OperationResult<>(0, null);
        }
    }

    @GetMapping(value = "queryApplicationSystem", produces = "application/json;charset=utf-8")
    public OperationResult<ApplicationSystemDataVO> queryApplicationSystem(@RequestParam String systemId,
                                                                           @RequestParam String systemName,
                                                                           @RequestParam String page,
                                                                           @RequestParam String limit) {
        String cacheKey = "queryGatewayServer:" + systemId + ":" + systemName + ":"+page + ":" + limit;
        OperationResult<ApplicationSystemDataVO> cachedData = getFromCache(cacheKey);
        if (cachedData != null) {
            logger.info("在queryGatewayServer的缓存中找到数据: {}", cacheKey);
            return cachedData;
        }

        try {
            logger.info("查询应用系统信息开始 systemId：{} systemName：{} page：{} limit：{}", systemId, systemName, page, limit);
            OperationRequest<ApplicationSystemDataVO> req = new OperationRequest<>(page, limit);
            req.setData(new ApplicationSystemDataVO(systemId, systemName));
            OperationResult<ApplicationSystemDataVO> operationResult = dataOperationManageService.queryApplicationSystem(req);
            // Save data to cache
            saveToCache(cacheKey, operationResult);
            logger.info("查询应用系统信息完成 operationResult：{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            logger.error("查询应用系统信息异常 systemId：{} systemName：{}", systemId, systemId, e);
            return new OperationResult<>(0, null);
        }
    }

    @GetMapping(value = "queryApplicationInterface", produces = "application/json;charset=utf-8")
    public OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(@RequestParam String systemId,
                                                                                 @RequestParam String interfaceId,
                                                                                 @RequestParam String page,
                                                                                 @RequestParam String limit) {
        String cacheKey = "queryGatewayServer:" + systemId + ":" + interfaceId + ":"+page + ":" + limit;
        OperationResult<ApplicationInterfaceDataVO> cachedData = getFromCache(cacheKey);
        if (cachedData != null) {
            logger.info("在queryGatewayServer的缓存中找到数据: {}", cacheKey);
            return cachedData;
        }

        try {
            logger.info("查询应用接口信息开始 systemId：{} interfaceId：{} page：{} limit：{}", systemId, interfaceId, page, limit);
            OperationRequest<ApplicationInterfaceDataVO> req = new OperationRequest<>(page, limit);
            req.setData(new ApplicationInterfaceDataVO(systemId, interfaceId));
            OperationResult<ApplicationInterfaceDataVO> operationResult = dataOperationManageService.queryApplicationInterface(req);
            // Save data to cache
            saveToCache(cacheKey, operationResult);
            logger.info("查询应用接口信息完成 operationResult：{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            logger.error("查询应用接口信息异常 systemId：{} interfaceId：{}", systemId, interfaceId, e);
            return new OperationResult<>(0, null);
        }
    }

    @GetMapping(value = "queryApplicationInterfaceMethodList", produces = "application/json;charset=utf-8")
    public OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodList(@RequestParam String systemId,
                                                                                                 @RequestParam String interfaceId,
                                                                                                 @RequestParam String page,
                                                                                                 @RequestParam String limit) {
        String cacheKey = "queryGatewayServer:" + systemId + ":" + interfaceId + ":"+page + ":" + limit;
        OperationResult<ApplicationInterfaceMethodDataVO> cachedData = getFromCache(cacheKey);
        if (cachedData != null) {
            logger.info("在queryGatewayServer的缓存中找到数据: {}", cacheKey);
            return cachedData;
        }

        try {
            logger.info("查询应用接口方法信息开始 systemId：{} interfaceId：{} page：{} limit：{}", systemId, interfaceId, page, limit);
            OperationRequest<ApplicationInterfaceMethodDataVO> req = new OperationRequest<>(page, limit);
            req.setData(new ApplicationInterfaceMethodDataVO(systemId, interfaceId));
            OperationResult<ApplicationInterfaceMethodDataVO> operationResult = dataOperationManageService.queryApplicationInterfaceMethod(req);
            // Save data to cache
            saveToCache(cacheKey, operationResult);
            logger.info("查询应用接口方法信息完成 operationResult：{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            logger.error("查询应用接口方法信息异常 systemId：{} interfaceId：{}", systemId, interfaceId, e);
            return new OperationResult<>(0, null);
        }
    }

    // 从缓存中检索数据的辅助方法
    private <T> T getFromCache(String cacheKey) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return (T) operations.get(cacheKey);
    }

    // 将数据保存到缓存的辅助方法
    private void saveToCache(String cacheKey, Object data) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(cacheKey, data);
    }

}

