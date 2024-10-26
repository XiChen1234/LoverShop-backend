package com.example.lovershopbackend.strategy.register;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import com.example.lovershopbackend.common.enums.UserStatusEnum;
import com.example.lovershopbackend.controller.request.RegisterRequest;
import com.example.lovershopbackend.mapper.LoginMapper;
import com.example.lovershopbackend.mapper.UserMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import com.example.lovershopbackend.mapper.entity.User;
import com.example.lovershopbackend.util.RandomUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description 账户注册审批策略实现
 */
@Component
public class AccountRegisterStrategy implements RegisterStrategy {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginMapper loginMapper;

    @Override
    public String register(RegisterRequest request) {
        String password = request.getPassword();
        String confirm = request.getConfirm();
        if (password == null || confirm == null) {
            throw new CommonException(ResponseCodeEnum.REGISTER_ERROR, "输入数据不能为空");
        }

        if (!password.equals(confirm)) {
            throw new CommonException(ResponseCodeEnum.REGISTER_ERROR);
        }

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
        login.setStatus(UserStatusEnum.AUDIT.getCode());
        loginMapper.insert(login);

        return username;
    }
}
