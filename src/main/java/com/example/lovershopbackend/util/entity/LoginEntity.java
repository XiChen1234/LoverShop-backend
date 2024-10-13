package com.example.lovershopbackend.util.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 登陆接收实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity {
    @JsonProperty("openid")
    private String openId;
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("errcode")
    private Integer errorCode;
    @JsonProperty("errmsg")
    private String errorMessage;
}
