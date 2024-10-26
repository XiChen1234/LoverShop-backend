package com.example.lovershopbackend.common.enums;

import lombok.Getter;

/**
 * @Description 用户状态枚举
 */
@Getter
public enum UserStatusEnum {
    NORMAL(0, "normal"),
    AUDIT(1, "audit"),
    FROZEN(2, "frozen"),
    LOGOUT(3, "log out");

    private final Integer code;
    private final String status;

    UserStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }
}
