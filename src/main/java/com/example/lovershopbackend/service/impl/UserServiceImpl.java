package com.example.lovershopbackend.service.impl;

import com.example.lovershopbackend.controller.response.UserResponse;
import com.example.lovershopbackend.dao.model.User;
import com.example.lovershopbackend.dao.repo.UserRepo;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.util.WechatUtil;
import com.example.lovershopbackend.util.entity.LoginEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 用户service模块具体实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private WechatUtil wechatUtil;
    @Resource
    private UserRepo userRepo;

    /**
     * 登陆接口
     *
     * @param code 用户临时code
     * @return 用户信息
     */
    @Override
    public UserResponse login(String code) {
        LoginEntity login = wechatUtil.sendLoginRequest(code);// 登陆实体，包含openid和session_key
        // 去数据库层查询用户是否存在
        User user = userRepo.selectByOpenId(login.getOpenId());
        UserResponse userResponse = new UserResponse();

        // 注册逻辑
        if (user == null) {
            register(login);
        }

        user = userRepo.selectByOpenId(login.getOpenId());
        userResponse.setUserId(user.getUserId());
        userResponse.setOpenId(login.getOpenId());
        userResponse.setUsername(user.getUsername());
        userResponse.setAvatarUrl(user.getAvatarUrl());
        userResponse.setMotto(user.getMotto());

        return userResponse;
    }

    /**
     * 注册接口（暂不开放）
     *
     * @param login 登陆信息，包含openid和session_key
     *              个人信息使用默认值生成
     */
    public void register(LoginEntity login) {
        User newUser = new User();
        newUser.setOpenId(login.getOpenId());
        newUser.setUsername("用户" + login.getOpenId().substring(0, 3)); //todo 默认用户名
        newUser.setAvatarUrl("/image/icon/emotion.png"); //todo 默认头像链接
        newUser.setMotto("这个用户很懒，没有写任何话"); //todo 默认座右铭
        newUser.setSessionKey(login.getSessionKey());
        userRepo.insertUser(newUser);
    }
}
