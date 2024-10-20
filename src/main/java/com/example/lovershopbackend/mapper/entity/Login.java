package com.example.lovershopbackend.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description 用户登陆实体类，与数据库中字段一一对应
 */
@Data
@TableName("sys_login")
public class Login {
    @TableId("id")
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("phone")
    private String phone;
    @TableField("email")
    private String email;
}
