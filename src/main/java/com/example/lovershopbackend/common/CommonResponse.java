package com.example.lovershopbackend.common;

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

    public static <T> CommonResponse<T> creatForSuccess() {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), null);
    }

    public static <T> CommonResponse<T> creatForSuccessData(T data) {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS.getCode(), null, data);
    }

    public static <T> CommonResponse<T> creatForError() {
        return new CommonResponse<>(ResponseCodeEnum.ERROR.getCode(), ResponseCodeEnum.ERROR.getDesc());
    }

    public static <T> CommonResponse<T> creatForError(String desc) {
        return new CommonResponse<>(ResponseCodeEnum.ERROR.getCode(), desc);
    }


    public static <T> CommonResponse<T> creatForFail() {
        return new CommonResponse<>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getDesc());
    }

    public static <T> CommonResponse<T> creatForFail(String desc) {
        return new CommonResponse<>(ResponseCodeEnum.FAIL.getCode(), desc);
    }
}
