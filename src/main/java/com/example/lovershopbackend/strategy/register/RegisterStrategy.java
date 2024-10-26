package com.example.lovershopbackend.strategy.register;

import com.example.lovershopbackend.controller.request.RegisterRequest;

/**
 * @Description 注册策略接口
 */
public interface RegisterStrategy {
    /**
     * 注册接口
     *
     * @param request 注册请求，根据不同类型包含不同数据
     * @return 注册成功的用户的username，需要告知用户
     * 使用邮箱、电话注册的用户会生成账号，也可以使用账号进行登陆
     */
    String register(RegisterRequest request);
}
