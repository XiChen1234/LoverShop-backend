package com.example.lovershopbackend.controller;

import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.response.UserResponse;
import com.example.lovershopbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 用户模块
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public CommonResponse<UserResponse> login(@RequestBody LoginRequest request) {
        String code = request.getCode();
        UserResponse login = userService.login(code);

        return CommonResponse.creatForSuccessData(login);
    }
}
