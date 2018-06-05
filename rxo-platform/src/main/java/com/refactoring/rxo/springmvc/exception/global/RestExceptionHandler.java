package com.refactoring.rxo.springmvc.exception.global;/**
 * Created by fei.yao on 2018/1/3.
 */

import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理器
 *
 * @author fei.yao
 * @create 2018-01-03 15:05
 **/
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class RestExceptionHandler {
    /**
     * 默认统一异常处理方法
     *
     * @param e 默认Exception异常对象
     * @return
     */
    @ExceptionHandler
    @ResponseStatus
    public ApiResult runtimeExceptionHandler(Exception e) {
        return ApiResultGenerator.errorResult(e.getMessage(), e);
    }
}
