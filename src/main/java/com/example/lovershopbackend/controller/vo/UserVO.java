package com.example.lovershopbackend.controller.vo;

import com.example.lovershopbackend.mapper.entity.User;
import lombok.Data;

/**
 * @Description 用户视图对象
 */
@Data
public class UserVO {
    private Long id;
    private String username; // 用户昵称
    private String avatarUrl; // 用户头像链接
    private String motto; // 用户个性签名

    /**
     * 将DO转化为VO
     *
     * @param user 用户DO
     * @return 用户VO
     */
    public static UserVO toVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setAvatarUrl(user.getAvatarUrl());
        userVO.setMotto(user.getMotto());
        return userVO;
    }
}
