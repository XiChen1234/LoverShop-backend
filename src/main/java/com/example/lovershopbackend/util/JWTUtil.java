package com.example.lovershopbackend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description JWT的工具类
 */
public class JWTUtil {
    private static final String SECRET_KEY = "zlc66752878";
    private static final Long EXPIRES_TIME = 60 * 60L; // 过期时间60min

    public static String createToken(Long userId) {
        return JWT.create()
                .withClaim("userId", userId)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(EXPIRES_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }


    public static Map<String, Object> parseToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token)
                    .getClaim("claims").asMap();
        }catch (JWTDecodeException | TokenExpiredException e) {  // token解析错误，或token过期
            e.printStackTrace();
            throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
