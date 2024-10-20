package com.example.lovershopbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lovershopbackend.mapper.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 用户层mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
