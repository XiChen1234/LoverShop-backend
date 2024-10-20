package com.example.lovershopbackend.common.enums;

import lombok.Getter;

/**
 * @Description 登陆类型枚举类
 */
@Getter
public enum LoginTypeEnum {
    ACCOUNT_PASSWORD(0, "account_password"),
    PHONE_CODE(1, "phone_code"),
    EMAIL_CODE(2, "email_code"),
    WECHAT(3, "wechat"); // 微信暂时不会设计

    private final Integer code;
    private final String type;

    LoginTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}
