package com.example.lovershopbackend.controller;

import com.example.lovershopbackend.dao.mapper.UserMapper;
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
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}