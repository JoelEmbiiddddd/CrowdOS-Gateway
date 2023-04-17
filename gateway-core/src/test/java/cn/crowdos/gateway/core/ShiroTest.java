package cn.crowdos.gateway.core;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.crowdos.gateway.core.authorization.IAuth;
import cn.crowdos.gateway.core.authorization.JwtUtil;
import cn.crowdos.gateway.core.authorization.auth.AuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * @File : ShiroTest.py
 * @Author : LiXin Huang, NWPU
 * @Desc :
 * @Email : iHuanglixin@outlook.com
 */

public class ShiroTest {

    private final static Logger logger = LoggerFactory.getLogger(ShiroTest.class);

    @Test
    public void test_auth_service() {
        IAuth auth = new AuthService();
        boolean validate = auth.validate("xiaoming", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4aWFvbWluZyIsImV4cCI6MTY4MjMwNDE0MywiaWF0IjoxNjgxNjk5MzQzLCJrZXkiOiJ4aWFvbWluZyJ9.PceRizWaMuZT3IOCNye8YxJphjIoOj7iX3ytYP77KiE");
        System.out.println(validate ? "验证成功" : "验证失败");
    }

    @Test
    public void test_awt() {
        String issuer = "xiaoming";
        long ttlMillis = 7 * 24 * 60 * 60 * 1000L;
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "xiaoming");

        // 编码
        String token = JwtUtil.encode(issuer, ttlMillis, claims);
        System.out.println(token);

        // 解码
        Claims parser = JwtUtil.decode(token);
        System.out.println(parser.getSubject());
    }

}
