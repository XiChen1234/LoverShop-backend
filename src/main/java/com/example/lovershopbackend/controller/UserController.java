package com.example.lovershopbackend.controller;

import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.annotation.Authorize;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.request.RegisterRequest;
import com.example.lovershopbackend.controller.request.UserInfoRequest;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 用户模块接口
 */
@RestController
@RequestMapping("/api/user")
@Authorize
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @Authorize(required = false)
    public CommonResponse<String> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    @Authorize(required = false)
    public CommonResponse<String> register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("")
    public CommonResponse<UserVO> getUserInfo(@RequestBody UserInfoRequest request) {
        return userService.getUserInfo(request.getUserId());
    }
}
