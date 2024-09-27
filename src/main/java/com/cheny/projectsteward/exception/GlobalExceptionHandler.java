package com.cheny.projectsteward.exception;

import com.cheny.projectsteward.common.BaseResponse;
import com.cheny.projectsteward.common.ErrorCode;
import com.cheny.projectsteward.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    /**
     * 参数非法校验
     * @param ex 参数异常
     * @return BaseResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        try {
            List<ObjectError> errors = ex.getBindingResult().getAllErrors();
            String message = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
            log.error("param illegal: {}", message);
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, message);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
        }
    }
}
