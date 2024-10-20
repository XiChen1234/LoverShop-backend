package com.example.lovershopbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.UserMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import com.example.lovershopbackend.mapper.entity.User;
import com.example.lovershopbackend.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginMapper loginMapper;

    @Override
    public CommonResponse<UserVO> login(LoginRequest request) {
        QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Login::getUsername, request.getUsername())
                .eq(Login::getPassword, request.getPassword());
        Login login = loginMapper.selectOne(queryWrapper);
        if (login == null) {
            return CommonResponse.createForError(ResponseCodeEnum.LOGIN_ERROR);
        }

        User user = userMapper.selectById(login.getUserId());

        return CommonResponse.createForSuccessData(UserVO.toVO(user));
    }

    @Override
    public CommonResponse<UserVO> getUserInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return CommonResponse.createForError(ResponseCodeEnum.NOT_FOUND);
        }
        return CommonResponse.createForSuccessData(UserVO.toVO(user));
    }
}
