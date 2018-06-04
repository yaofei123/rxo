package com.refactoring.rxo.springmvc;

/**
 * Created by fai.yao on 2016/11/29.
 */
public class ErrorResponse {

    public ErrorResponse(){}

    public ErrorResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
