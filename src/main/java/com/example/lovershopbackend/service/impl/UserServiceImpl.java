package com.example.lovershopbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.controller.request.RegisterRequest;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.factory.LoginStrategyFactory;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.UserMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import com.example.lovershopbackend.mapper.entity.User;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.strategy.LoginStrategy;
import com.example.lovershopbackend.util.JWTUtil;
import com.example.lovershopbackend.util.RandomUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;

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
        // 校验密码是否符合要求
        if (!Objects.equals(request.getPassword(), request.getConfirm())) {
            return CommonResponse.createForError(ResponseCodeEnum.REGISTER_ERROR);
        }
        // 注册逻辑
        User user = new User();
        user.setUsername("游客用户");
        user.setAvatarUrl("/demo/moren.jpg");
        user.setMotto("这个人很懒，没有写任何东西");
        userMapper.insert(user);
        long userId = user.getId();

        Login login = new Login();
        login.setUserId(userId);

        // 生成账号
        Login currentLogin;
        String username;
        do {
            username = RandomUtil.get8Random();
            QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Login::getUsername, username);
            currentLogin = loginMapper.selectOne(queryWrapper);
        } while (currentLogin != null);

        login.setUsername(username);
        login.setPassword(request.getPassword());
        loginMapper.insert(login);

        return CommonResponse.createForSuccessData(login.getUsername());
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
