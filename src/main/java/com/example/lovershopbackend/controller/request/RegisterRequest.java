package com.example.lovershopbackend.controller.request;

import lombok.Data;

/**
 * @Description 注册请求
 */
@Data
public class RegisterRequest {
    private String password;
    private String confirm;
}
