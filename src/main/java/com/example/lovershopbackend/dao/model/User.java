package com.example.lovershopbackend.dao.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 用户信息实体对象
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId("user_id")
    private Long userId;
    @TableField("open_id")
    private String openId;
    private String username;
    @TableField("avatar_url")
    private String avatarUrl;
    private String motto;
    @TableField("session_key")
    private String sessionKey;
}
