package com.afeng.framework.exception;


import org.springframework.http.HttpStatus;

/**
 * 自定义API异常
 */
public class ApiException extends RuntimeException {

    private String msg;
    private Object data;
    private int code = 500;

    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ApiException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ApiException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ApiException(String msg, int code, Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ApiException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public static ApiException toAccessDenied() {
        return new ApiException("无权限", HttpStatus.FORBIDDEN.value());
    }

    public static ApiException toAccessDenied(String msg) {
        return new ApiException(msg, HttpStatus.FORBIDDEN.value());
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
