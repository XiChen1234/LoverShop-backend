package com.example.lovershopbackend.controller.request;

import com.example.lovershopbackend.common.enums.LoginTypeEnum;
import lombok.Data;

/**
 * @Description 登陆请求
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verify;
    // 微信暂时不会设计
    private LoginTypeEnum type;
}
