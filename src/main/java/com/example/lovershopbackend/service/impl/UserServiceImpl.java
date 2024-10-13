package com.example.lovershopbackend.service.impl;

import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.ResponseCodeEnum;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.dao.model.User;
import com.example.lovershopbackend.dao.repo.UserRepo;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.service.dto.UserDTO;
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
    public CommonResponse<UserVO> login(String code) {
        LoginEntity login = wechatUtil.sendLoginRequest(code);// 登陆实体，包含openid和session_key
        if (login.getErrorCode() != null) {
            throw new CommonException(login.getErrorCode(), "临时code错误");
        }
        // 去数据库层查询用户是否存在
        UserDTO userDTO = userRepo.selectByOpenId(login.getOpenId());

        // 注册逻辑
        if (userDTO == null) {
            register(login);
        }
        userDTO = userRepo.selectByOpenId(login.getOpenId()); // 重新获取用户对象，一定保证有数据

        return CommonResponse.createForSuccessData(UserDTO.toVO(userDTO)); // 转化为VO对象
    }

    /**
     * 注册接口（暂不开放）
     *
     * @param login 登陆信息，包含openid和session_key
     *              个人信息使用默认值生成
     */
    public void register(LoginEntity login) {
        UserDTO userDTO = new UserDTO();
        userDTO.setOpenId(login.getOpenId());
        userDTO.setUsername("用户" + login.getOpenId().substring(0, 3)); //todo 默认用户名
        userDTO.setAvatarUrl("/image/icon/emotion.png"); //todo 默认头像链接
        userDTO.setMotto("这个用户很懒，没有写任何话"); //todo 默认座右铭
        userDTO.setSessionKey(login.getSessionKey());
        User newUser = UserDTO.toModel(userDTO);
        userRepo.insertUser(newUser);
    }
}
