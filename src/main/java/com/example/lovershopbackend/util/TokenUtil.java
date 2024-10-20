package com.example.lovershopbackend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.lovershopbackend.config.SecretConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Map;

/**
 * @Description token生成与验证工具类
 */
@Component
public class TokenUtil {

    @Resource
    private SecretConfig secretConfig;

    /**
     * 接收负载并生成token返回
     *
     * @param claims 负载map
     * @return token字符串
     */
    public String getToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims) // 负载map
                .withIssuedAt(Instant.now()) // 签发时间现在
                .withExpiresAt(Instant.now().plusMillis(60)) // 过期时间60min
                .sign(Algorithm.HMAC256(secretConfig.getTokenSecret())); // 签名密钥
    }

    /**
     * 校验token并返回业务数据
     *
     * @param token token字符串
     * @return 业务数据Map封装
     */
    public Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(secretConfig.getTokenSecret()))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
