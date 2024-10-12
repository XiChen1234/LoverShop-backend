package com.example.lovershopbackend.service.dto;

import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.dao.model.User;
import lombok.Data;

/**
 * @Description 用户数据传输对象
 */
@Data
public class UserDTO {
    private Long userId;
    private String openId;
    private String username;
    private String avatarUrl;
    private String motto;
    private String sessionKey;

    public static UserVO toVO(UserDTO userDTO) {
        UserVO userVO = new UserVO();
        userVO.setUserId(userDTO.getUserId());
        userVO.setOpenId(userDTO.getOpenId());
        userVO.setUsername(userDTO.getUsername());
        userVO.setAvatarUrl(userDTO.getAvatarUrl());
        userVO.setMotto(userDTO.getMotto());
        return userVO;
    }

    public static User toModel(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setOpenId(userDTO.getOpenId());
        user.setUsername(userDTO.getUsername());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setMotto(userDTO.getMotto());
        user.setSessionKey(userDTO.getSessionKey());
        return user;
    }

    public static UserDTO fromModel(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setOpenId(user.getOpenId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setMotto(user.getMotto());
        userDTO.setSessionKey(user.getSessionKey());
        return userDTO;
    }

}
