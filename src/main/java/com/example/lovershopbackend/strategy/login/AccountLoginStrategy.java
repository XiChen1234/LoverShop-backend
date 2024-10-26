package com.example.lovershopbackend.strategy.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description 账号密码登陆策略
 */
@Component
public class AccountLoginStrategy implements LoginStrategy {
    @Resource
    private LoginMapper loginMapper;

    @Override
    public Long login(LoginRequest request) {
        QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Login::getUsername, request.getUsername())
                .eq(Login::getPassword, request.getPassword());
        Login login = loginMapper.selectOne(queryWrapper);
        return login.getUserId();
    }
}
