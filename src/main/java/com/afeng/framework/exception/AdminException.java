package com.afeng.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 自定义管理后台异常
 */
public class AdminException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public AdminException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AdminException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AdminException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public AdminException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public static AdminException toAccessDenied() {
        return new AdminException("无权限", HttpStatus.FORBIDDEN.value());
    }

    public static AdminException toAccessDenied(String msg) {
        return new AdminException(msg, HttpStatus.FORBIDDEN.value());
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


}
