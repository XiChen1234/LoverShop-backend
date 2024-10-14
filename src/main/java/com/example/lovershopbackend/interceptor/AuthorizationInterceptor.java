package com.example.lovershopbackend.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.lovershopbackend.exception.CommonException;
import com.example.lovershopbackend.exception.ResponseCodeEnum;
import com.example.lovershopbackend.common.annotation.Authorize;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description token验证处理器
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法则直接通过
        if(!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();

        if(method.isAnnotationPresent(Authorize.class)) {
            Authorize authorize = method.getAnnotation(Authorize.class);
            if(authorize.required()) {
                String token = request.getHeader("authorization");

                if(token == null) {
                    throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "尚未登陆，请登陆");
                }

                try {
                    Long userId = JWT.decode(token).getClaim("userId").as(Long.class);
                    UserVO user = userService.getUserInfo(userId).getData();
                    if (user == null) {
                        throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "用户不存在，请重新登陆");
                    }

                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getOpenId())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "token校验失败，请重新登陆");
                    }

                    return true;
                } catch (JWTDecodeException e) {
                    throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "token解析失败，请重新登陆");
                }
            }
        }

        return true;
    }
}
