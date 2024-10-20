package com.example.lovershopbackend.handler;

import com.example.lovershopbackend.common.CommonException;
import com.example.lovershopbackend.common.CommonResponse;
import com.example.lovershopbackend.common.enums.ResponseCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理器
 * Exception作为兜底异常进行处理
 */

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public CommonResponse<?> commonException(CommonException commonException) {
        return CommonResponse.createForError(commonException);
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse<?> exception(Exception exception) {
        exception.printStackTrace();
        return CommonResponse.createForFail(ResponseCodeEnum.FAIL);
    }
}