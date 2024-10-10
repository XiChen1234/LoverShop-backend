package com.example.lovershopbackend.util;

import com.example.lovershopbackend.config.SecretConfig;
import com.example.lovershopbackend.util.entity.LoginEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 微信工具类
 */
@Component
public class WechatUtil {

    private static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private SecretConfig secretConfig;
    @Resource
    private RestTemplate restTemplate;

    public LoginEntity sendLoginRequest(String code) {
        // 将code发往微信服务器，获取用户信息
        String target = LOGIN_URL +
                "?appid=" + secretConfig.getAppId() +
                "&secret=" + secretConfig.getAppSecret() +
                "&js_code=" + code +
                "&grant_type=authorization_code";

        ResponseEntity<LoginEntity> responseEntity = restTemplate.getForEntity(target, LoginEntity.class);
        return responseEntity.getBody();
    }
}
