package cn.crowdos.gateway.center.domain.message;

import cn.crowdos.gateway.center.application.IMessageService;
import cn.crowdos.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @File : IMessageServiceImpl.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 消息服务
 * @Email : iHuanglixin@outlook.com
 */

@Service
public class IMessageServiceImpl implements IMessageService {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Resource
    private Publisher publisher;

    @Override
    public Map<String, String> queryRedisConfig() {
        return new HashMap<String, String>() {{
            put("host", host);
            put("port", String.valueOf(port));
        }};
    }

    @Override
    public void pushMessage(String gatewayId, Object message) {
        publisher.pushMessage(gatewayId, message);
    }

}
