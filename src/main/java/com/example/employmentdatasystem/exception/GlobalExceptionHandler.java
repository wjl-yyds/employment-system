package com.example.employmentdatasystem.exception;

import com.example.employmentdatasystem.common.ApiResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ApiResponse<Void> handleBiz(BizException ex) { return ApiResponse.fail(ex.getCode(), ex.getMessage()); }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValid(MethodArgumentNotValidException ex) {
        FieldError fe = ex.getBindingResult().getFieldError();
        return ApiResponse.fail(4001, fe == null ? "参数不合法" : fe.getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Void> handleReadable() { return ApiResponse.fail(4002, "请求体格式错误"); }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleEx() { return ApiResponse.fail(5000, "系统异常，请稍后重试"); }
}