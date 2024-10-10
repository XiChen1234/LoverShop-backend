package com.example.lovershopbackend.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lovershopbackend.dao.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 用户数据库层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
