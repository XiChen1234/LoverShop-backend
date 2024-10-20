package com.example.lovershopbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lovershopbackend.mapper.entity.Login;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 登陆层mapper
 */
@Mapper
public interface LoginMapper extends BaseMapper<Login> {
}
