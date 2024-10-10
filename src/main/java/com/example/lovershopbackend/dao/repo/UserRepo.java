package com.example.lovershopbackend.dao.repo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.dao.model.User;
import com.example.lovershopbackend.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 用户数据库访问repo层
 */
@Service
public class UserRepo {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过openid查找用户
     * @param openId 微信公众平台提供的openid
     * @return 用户信息
     */
    public User selectByOpenId(String openId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getOpenId, openId);

        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 插入用户
     * @param user 用户对象
     * @return 影响行数，正常为1
     */
    public int insertUser(User user) {
        return userMapper.insert(user);
    }
}
