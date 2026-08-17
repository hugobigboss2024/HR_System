package com.hrm.hrm_backend.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hrm.hrm_backend.dto.Result;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 捕獲自訂業務異常
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.error("Business anomaly: {}", e.getMessage());
        // return Result.error(e.getCode(), e.getMessage());
        Integer code = e.getCode() != null ? e.getCode() : 500;
        return Result.error(code, e.getMessage());
    }

    // 捕獲SpringBoot@Valid/@Validated參數校驗失敗異常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.warn("Parameter validation failed: {}", errors);
        return Result.error(400, "Incorrect request parameter format", errors);
    }

    // 捕獲未知/未處理的系統異常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleGeneralException(Exception e) {
        log.error("The system did not catch the exception.: ", e);
        // return Result.error(500, "The system is busy, please try again later.: " + e.getMessage());
        return Result.error(500, "The system is busy, please try again later.: "); // 移除e.getMessage()對外只返回通用安全提示
    }
}
