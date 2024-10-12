package com.example.lovershopbackend.controller.vo;

import lombok.Data;

/**
 * @Description 用户信息响应封装
 */
@Data
public class UserVO {
    private Long userId;
    private String openId;
    private String username;
    private String avatarUrl;
    private String motto;
}
