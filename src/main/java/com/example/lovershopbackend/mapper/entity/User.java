package com.example.lovershopbackend.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description 用户实体类，与数据库中字段一一对应
 */
@Data
@TableName("user")
public class User {
    @TableId("id")
    private Long id;
    @TableField("username")
    private String username;
    @TableField("avatar_url")
    private String avatarUrl;
    @TableField("motto")
    private String motto;
}
