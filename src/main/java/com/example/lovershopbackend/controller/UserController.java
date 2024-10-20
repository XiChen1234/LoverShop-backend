package com.example.lovershopbackend.controller;

import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.annotation.Authorize;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.request.UserInfoRequest;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 用户模块相关接口
 */

@Authorize
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登陆接口
     * @param request 登陆请求，封装微信小程序用于登陆的code
     * @return 用户信息VO
     */
    @PostMapping("/login")
    public CommonResponse<UserVO> login(@RequestBody LoginRequest request) {
        return userService.login(request.getCode());
    }

    @PostMapping("")
    public CommonResponse<UserVO> getUserInfo(@RequestBody UserInfoRequest request) {
        return userService.getUserInfo(request.getUserId());
    }
}
