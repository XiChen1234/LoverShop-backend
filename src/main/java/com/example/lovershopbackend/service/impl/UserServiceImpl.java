package com.example.lovershopbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.request.RegisterRequest;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.factory.LoginStrategyFactory;
import com.example.lovershopbackend.factory.RegisterStrategyFactory;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.UserMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import com.example.lovershopbackend.mapper.entity.User;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.strategy.login.LoginStrategy;
import com.example.lovershopbackend.strategy.register.RegisterStrategy;
import com.example.lovershopbackend.util.JWTUtil;
import com.example.lovershopbackend.util.RandomUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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
    @Resource
    private RegisterStrategyFactory registerStrategyFactory;

    @Override
    public CommonResponse<String> login(LoginRequest request) {
        LoginStrategy strategy = loginStrategyFactory.getStrategy(request.getType());
        Long userId = strategy.login(request);
        if (userId == null) {
            return CommonResponse.createForError(ResponseCodeEnum.LOGIN_ERROR);
        }
        String token = JWTUtil.createToken(userId);
        return CommonResponse.createForSuccessData(token);
    }

    @Override
    public CommonResponse<String> register(RegisterRequest request) {
        RegisterStrategy strategy = registerStrategyFactory.getStrategy(request.getType());
        String username = strategy.register(request);
        if (username == null) {
            return CommonResponse.createForError
                    (ResponseCodeEnum.REGISTER_ERROR, "注册失败，有效username不足");
        }
        return CommonResponse.createForSuccessData(username);
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
