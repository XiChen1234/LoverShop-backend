package com.example.lovershopbackend.controller.vo;

import com.example.lovershopbackend.dao.model.User;
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

    public static UserVO fromModel(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setOpenId(user.getOpenId());
        userVO.setUsername(user.getUsername());
        userVO.setAvatarUrl(user.getAvatarUrl());
        userVO.setMotto(user.getMotto());
        return userVO;
    }
}
