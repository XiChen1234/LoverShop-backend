package com.example.lovershopbackend.service;

import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.request.RegisterRequest;
import com.example.lovershopbackend.controller.vo.UserVO;

/**
 * @Description 用户业务接口
 */
public interface UserService {
    /**
     * 用户登陆接口
     *
     * @param request 登录请求
     * @return 用户信息
     */
    CommonResponse<String> login(LoginRequest request);

    /**
     * 用户注册接口
     *
     * @param request 注册请求
     * @return 用户注册成功时分配的账号
     */
    CommonResponse<String> register(RegisterRequest request);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    CommonResponse<UserVO> getUserInfo(Long userId);
}
