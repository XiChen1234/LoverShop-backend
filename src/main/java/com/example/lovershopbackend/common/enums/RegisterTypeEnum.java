package com.example.lovershopbackend.common.enums;

import lombok.Getter;

/**
 * @Description 注册枚举类
 */
@Getter
public enum RegisterTypeEnum {
    ACCOUNT_PASSWORD(0, "account_password");

    private final Integer code;
    private final String type;

    RegisterTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}
