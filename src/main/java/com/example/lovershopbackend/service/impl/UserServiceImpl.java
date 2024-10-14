package com.example.lovershopbackend.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.controller.vo.UserVO;
import com.example.lovershopbackend.dao.mapper.UserMapper;
import com.example.lovershopbackend.dao.model.User;
import com.example.lovershopbackend.service.UserService;
import com.example.lovershopbackend.util.WechatUtil;
import com.example.lovershopbackend.util.entity.LoginEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @Description 用户service模块具体实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private WechatUtil wechatUtil;
    @Resource
    private UserMapper userMapper;

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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getOpenId, login.getOpenId());
        User user = userMapper.selectOne(queryWrapper);

        // 注册逻辑
        if (user == null) {
            user = new User();
            user.setOpenId(login.getOpenId());
            user.setUsername("用户" + login.getOpenId().substring(0, 3)); //todo 默认用户名
            user.setAvatarUrl("/image/icon/emotion.png"); //todo 默认头像链接
            user.setMotto("这个用户很懒，没有写任何话"); //todo 默认座右铭
            user.setSessionKey(login.getSessionKey());
            user.setRegisterTime(LocalDateTime.now());
            user.setLoginTime(LocalDateTime.now());
            userMapper.insert(user);
        } else {
            user.setLoginTime(LocalDateTime.now());
            userMapper.updateById(user);
        }
        UserVO userVO = UserVO.fromModel(user);
        String token = JWT.create().withAudience("user") // 用户模块的jwt
                .withClaim("userId", user.getUserId()) // 用户id
                .withIssuedAt(Instant.now()) // 签发时间现在
                .withExpiresAt(Instant.now().plusMillis(60)) // 过期时间60min
                .sign(Algorithm.HMAC256(user.getOpenId())); // 签名密钥，每个人能不一样);
        userVO.setToken(token);

        return CommonResponse.createForSuccessData(userVO); // 转化为VO对象
    }

    /**
     * 用户信息查询接口
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public CommonResponse<UserVO> getUserInfo(Long userId) {
        // todo 用户信息查询接口
        return CommonResponse.createForSuccessData(new UserVO());
    }
}
