package com.example.lovershopbackend.service;

import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.controller.vo.UserVO;

/**
 * @Description 用户service模块
 */
public interface UserService {
    CommonResponse<UserVO> login(String code);

    CommonResponse<UserVO> getUserInfo(Long userId);
}
