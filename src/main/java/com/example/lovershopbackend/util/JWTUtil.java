package com.example.lovershopbackend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
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


    public static Long parseToken(String token) {
        DecodedJWT decodedJWT;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (JWTDecodeException e) {  // token解析错误，或token过期
            e.printStackTrace();
            throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "token无效");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "token已过期");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return decodedJWT.getClaim("userId").asLong();
    }
}
