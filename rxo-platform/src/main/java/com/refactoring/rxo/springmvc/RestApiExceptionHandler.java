package com.refactoring.rxo.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * 集中处理RestAPI的异常
 */
@ControllerAdvice
public class RestApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidRequestError(MethodArgumentNotValidException ex) {

        LOGGER.error("", ex);

        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();


        String message = "";
        if (errorList.size() > 0) {
            for (ObjectError error : errorList) {
                message += ";" + error.getDefaultMessage();
            }
        }
        message = message.replaceFirst(";", "");
        return new ErrorResponse("001", message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        LOGGER.error("", ex);
        return new ErrorResponse("002", "输入的请求参数有误,请核实后重新输入");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleBusinessException(BusinessException ex) {
        LOGGER.error("", ex);
        return new ErrorResponse("003", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleUnexpectedServerError(RuntimeException ex) {
        LOGGER.error("", ex);
        return new ErrorResponse("100", "操作失败, 请确认输入参数或联系管理员");
    }
}
