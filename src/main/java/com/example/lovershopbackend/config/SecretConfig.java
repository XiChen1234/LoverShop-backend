package com.example.lovershopbackend.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description 密钥配置类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@PropertySource("classpath:secret.yml")
public class SecretConfig {
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;
}
