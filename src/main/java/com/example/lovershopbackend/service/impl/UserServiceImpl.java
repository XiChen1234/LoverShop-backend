package com.example.lovershopbackend.service.impl;

import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.factory.LoginStrategyFactory;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.UserMapper;
import com.example.lovershopbackend.mapper.entity.User;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.strategy.LoginStrategy;
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
    @Resource
    private LoginStrategyFactory loginStrategyFactory;

    @Override
    public CommonResponse<UserVO> login(LoginRequest request) {
        LoginStrategy strategy = loginStrategyFactory.getStrategy(request.getType());
        Long userId = strategy.login(request);
        if (userId == null) {
            return CommonResponse.createForError(ResponseCodeEnum.LOGIN_ERROR);
        }

        User user = userMapper.selectById(userId);
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
