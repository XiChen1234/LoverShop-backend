package com.example.lovershopbackend.common;

import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import lombok.Getter;

/**
 * @Description 通用异常类
 */
@Getter
public class CommonException extends RuntimeException {
    private Integer code;
    private String message;

    public CommonException() {
        super();
    }

    public CommonException(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    /**
     * 自定义异常，包括响应码和响应信息
     *
     * @param code    响应码
     * @param message 响应信息
     */
    public CommonException(ResponseCodeEnum code, String message) {
        this.code = code.getCode();
        this.message = message;
    }

    /**
     * 自定义异常，包括响应码和响应信息
     *
     * @param code    响应码
     * @param message 响应信息
     */
    public CommonException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
