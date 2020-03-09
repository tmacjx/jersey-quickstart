package com.bokecc.entity;

public class ErrorCode {
    public static final ErrorCode OK = define(0, "ok");
    public static final ErrorCode UNKNOW_EXCEPTION = define(-100, "未知异常");

    private int code;
    private String msg;

    public ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode define(int code, String msg) {
        return new ErrorCode(code, msg);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
