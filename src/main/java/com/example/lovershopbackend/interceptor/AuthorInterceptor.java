package com.example.lovershopbackend.interceptor;

import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.annotation.Authorize;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.mapper.UserMapper;
import com.example.lovershopbackend.mapper.entity.User;
import com.example.lovershopbackend.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description token验证处理器
 */
@Component
public class AuthorInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Authorize authorize = handlerMethod.getMethodAnnotation(Authorize.class);
        if (authorize == null) {
            authorize = handlerMethod.getBean().getClass().getAnnotation(Authorize.class);
        }
        if (authorize != null && authorize.required()) {
            String token = request.getHeader("authorization");
            if (token == null) {
                throw new CommonException(ResponseCodeEnum.UNAUTHORIZED);
            }
            Long userId = JWTUtil.parseToken(token);
            User user = userMapper.selectById(userId);
            if(user == null) {
                throw new CommonException(ResponseCodeEnum.NOT_FOUND);
            }
        }
        return true;
    }
}