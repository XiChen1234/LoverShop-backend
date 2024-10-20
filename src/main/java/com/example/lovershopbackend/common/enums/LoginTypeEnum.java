package com.example.lovershopbackend.common.enums;

import lombok.Getter;

/**
 * @Description 登陆类型枚举类
 */
@Getter
public enum LoginTypeEnum {
    ACCOUNT_PASSWORD("account_password"),
    PHONE_CODE("phone_code"),
    EMAIL_CODE("email_code"),
    WECHAT("wechat"); // 微信暂时不会设计

    private final String type;

    LoginTypeEnum(String type) {
        this.type = type;
    }
}
