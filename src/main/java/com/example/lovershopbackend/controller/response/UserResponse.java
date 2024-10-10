package com.example.lovershopbackend.controller.response;

import lombok.Data;

/**
 * @Description 用户信息响应封装
 */
@Data
public class UserResponse {
    private Long userId;
    private String openId;
    private String username;
    private String avatarUrl;
    private String motto;
}
