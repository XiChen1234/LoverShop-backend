package com.example.lovershopbackend.common;

import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
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

    /**
     * 自定义成功响应
     * 默认、带信息、带数据
     */
    public static <T> CommonResponse<T> createForSuccess() {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), null);
    }

    public static <T> CommonResponse<T> createForSuccess(String message) {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), message);
    }

    public static <T> CommonResponse<T> createForSuccessData(T data) {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), null, data);
    }


    /**
     * 自定义错误响应
     * 默认、带信息、带异常
     */
    public static <T> CommonResponse<T> createForError() {
        return new CommonResponse<>(ResponseCodeEnum.ERROR.getCode(), ResponseCodeEnum.ERROR.getMessage());
    }

    public static <T> CommonResponse<T> createForError(String message) {
        return new CommonResponse<>(ResponseCodeEnum.ERROR.getCode(), message);
    }

    public static <T> CommonResponse<T> createForError(ResponseCodeEnum response) {
        return new CommonResponse<>(response.getCode(), response.getMessage());
    }

    public static <T> CommonResponse<T> createForError(ResponseCodeEnum response, String message) {
        return new CommonResponse<>(response.getCode(), message);
    }

    public static <T> CommonResponse<T> createForError(CommonException exception) {
        return new CommonResponse<>(exception.getCode(), exception.getMessage());
    }

    /**
     * 自定义失败响应
     * 默认、带信息
     */
    public static <T> CommonResponse<T> createForFail() {
        return new CommonResponse<>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getMessage());
    }

    public static <T> CommonResponse<T> createForFail(String message) {
        return new CommonResponse<>(ResponseCodeEnum.FAIL.getCode(), message);
    }

    public static <T> CommonResponse<T> createForFail(ResponseCodeEnum response) {
        return new CommonResponse<>(response.getCode(), response.getMessage());
    }

    public static <T> CommonResponse<T> createForFail(Exception exception) {
        return new CommonResponse<>(ResponseCodeEnum.FAIL.getCode(), exception.getMessage());
    }
}