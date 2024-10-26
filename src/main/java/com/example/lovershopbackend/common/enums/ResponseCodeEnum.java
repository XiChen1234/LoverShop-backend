package com.example.lovershopbackend.common.enums;

import lombok.Getter;

/**
 * @Description 响应码枚举类
 */
@Getter
public enum ResponseCodeEnum {
    // 响应码动态扩展
    SUCCESS(200, "请求成功"),
    ERROR(400, "请求错误"), // 我的业务异常

    LOGIN_ERROR(10001, "用户名或密码错误"),
    REGISTER_ERROR(10002, "注册时两次输入的密码不一致"),
    UNAUTHORIZED(10401, "鉴权异常"),
    NOT_FOUND(10404, "查询失败"),

    // 一切异常的兜底异常
    FAIL(500, "服务器内部异常");


    private final int code;
    private final String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
