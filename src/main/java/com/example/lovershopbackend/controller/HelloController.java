package com.example.lovershopbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lovershopbackend.dao.mapper.UserMapper;
import com.example.lovershopbackend.dao.model.User;
import com.example.lovershopbackend.dao.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 测试controller
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRepo userRepo;
    @GetMapping("/hello")
    public String hello() {
//        User user = userRepo.selectByOpenId("o713W4v6Shvw8ANSJ-jVKzczvYLg");
//        return user.getUsername();
        return "hello";
    }
}