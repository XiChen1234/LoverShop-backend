package com.example.lovershopbackend.service;

import com.example.lovershopbackend.controller.vo.UserVO;

/**
 * @Description 用户service模块
 */
public interface UserService {
    UserVO login(String code);
}
