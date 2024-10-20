package com.example.lovershopbackend.config;

import com.example.lovershopbackend.interceptor.AuthorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Description 鉴权拦截器配置
 */
@Configuration
public class AuthorInterceptorConfig implements WebMvcConfigurer {
    @Resource
    private AuthorInterceptor authorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorInterceptor);
    }
}