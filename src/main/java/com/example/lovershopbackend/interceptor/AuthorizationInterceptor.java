package com.example.lovershopbackend.interceptor;

import com.example.lovershopbackend.exception.CommonException;
import com.example.lovershopbackend.exception.ResponseCodeEnum;
import com.example.lovershopbackend.common.annotation.Authorize;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description token验证处理器
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法则直接通过
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Authorize authorize = handlerMethod.getMethodAnnotation(Authorize.class);
        if(authorize == null) {
            authorize = handlerMethod.getBean().getClass().getAnnotation(Authorize.class);
        }
        if(authorize != null && authorize.required()) {
            String token = request.getHeader("authorization");
            if(token == null) {
                throw new CommonException(ResponseCodeEnum.UNAUTHORIZED, "尚未登陆，请登陆");
            }
            // 补全token验证流程、
            Map<String, Object> claim = tokenUtil.parseToken(token);

        }
        return true;
    }
}
