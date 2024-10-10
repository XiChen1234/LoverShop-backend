package com.example.lovershopbackend.common;

import lombok.Getter;

/**
 * @Description 响应码枚举
 */
@Getter
public enum ResponseCodeEnum {
    // 响应码动态扩展
    SUCCESS(200, "SUCCESS"),    // 请求成功
    ERROR(400, "ERROR"),        // 请求错误，一般为资源请求方式错误
    FAIL(500, "FAIL");          // 请求失败，一般为服务器内部错误，更严重

    private final int code;
    private final String desc;

    ResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
