package com.example.lovershopbackend.strategy.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.common.enums.UserStatusEnum;
import com.example.lovershopbackend.controller.request.LoginRequest;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description 账号密码登陆策略
 */
@Component
public class AccountLoginStrategy implements LoginStrategy {
    @Resource
    private LoginMapper loginMapper;

    @Override
    public Long login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        if (username == null || password == null) {
            throw new CommonException(ResponseCodeEnum.LOGIN_ERROR, "用户名和密码不能为空");
        }

        QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Login::getUsername, request.getUsername())
                .eq(Login::getPassword, request.getPassword());
        Login login = loginMapper.selectOne(queryWrapper);

        if (Objects.equals(login.getStatus(), UserStatusEnum.AUDIT.getCode())) {
            throw new CommonException(ResponseCodeEnum.LOGIN_ERROR, "账号正在审核中，审核通过即可登陆");
        }
        if (Objects.equals(login.getStatus(), UserStatusEnum.FROZEN.getCode())) {
            throw new CommonException(ResponseCodeEnum.LOGIN_ERROR, "账号已被冻结，请联系管理员解冻");
        }

        return login.getUserId();
    }
}
