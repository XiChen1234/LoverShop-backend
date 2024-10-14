package com.example.lovershopbackend.common;

import com.example.lovershopbackend.exception.CommonException;
import com.example.lovershopbackend.exception.ResponseCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * @Description 通用响应类
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private final int status;
    private final String message;
    private T data;

    private CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private CommonResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> createForSuccess() {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), null);
    }

    /**
     * 自定义成功响应
     *
     * @param data 响应数据
     * @return 通用响应类
     */
    public static <T> CommonResponse<T> createForSuccessData(T data) {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), null, data);
    }

    public static <T> CommonResponse<T> createForError() {
        return new CommonResponse<>(ResponseCodeEnum.ERROR.getCode(), ResponseCodeEnum.ERROR.getDesc());
    }

    public static <T> CommonResponse<T> createForError(String message) {
        return new CommonResponse<>(ResponseCodeEnum.ERROR.getCode(), message);
    }

    /**
     * 自定义异常响应
     *
     * @param commonException 自定义异常（自定义错误码和信息）
     * @return 通用响应类
     */
    public static <T> CommonResponse<T> createForError(CommonException commonException) {
        return new CommonResponse<>(commonException.getCode(), commonException.getMessage());
    }

    /**
     * 兜底异常处理
     *
     * @return 通用响应类
     */
    public static <T> CommonResponse<T> createForFail() {
        return new CommonResponse<>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.ERROR.getDesc());
    }
}
