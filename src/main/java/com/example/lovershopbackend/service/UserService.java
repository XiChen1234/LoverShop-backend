package com.example.lovershopbackend.service;

import com.example.lovershopbackend.controller.response.UserResponse;

/**
 * @Description 用户service模块
 */
public interface UserService {
    UserResponse login(String code);
}
