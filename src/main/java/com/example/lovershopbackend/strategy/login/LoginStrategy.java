package com.example.lovershopbackend.strategy.login;

import com.example.lovershopbackend.controller.request.LoginRequest;

/**
 * @Description 登陆策略接口
 */
public interface LoginStrategy {
    Long login(LoginRequest request);
}
