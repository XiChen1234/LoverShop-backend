package com.example.lovershopbackend.controller.request;

import lombok.Data;

/**
 * @Description 注册请求
 */
@Data
public class RegisterRequest {
    private String password;
    private String confirm;

    private String email;
    private String verify;

    private Integer type; // 用于标识注册方式，0表示审批注册，1表示邮箱-验证码注册
}
