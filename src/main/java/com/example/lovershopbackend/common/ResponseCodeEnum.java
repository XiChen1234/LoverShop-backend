package com.example.lovershopbackend.common;

import lombok.Getter;

/**
 * @Description 响应码枚举
 */
@Getter
public enum ResponseCodeEnum {
    // 响应码动态扩展
    SUCCESS(200, "请求成功"),
    ERROR(400, "请求错误"), // 我的业务异常

    // 微信相关异常
    WX_INVALID_CODE_ERROR(40029, "临时code错误"),
    WX_USED_CODE_ERROR(40163, "登陆code已经被使用"),

    FAIL(500, "系统异常");

    private final int code;
    private final String desc;

    ResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
