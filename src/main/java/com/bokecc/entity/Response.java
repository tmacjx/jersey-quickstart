package com.bokecc.entity;

//通用请求响应类

public class Response<T> {
    private int status = 0;
    private String msg = "";
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(ErrorCode code) {
        if (null != code) {
            this.status = code.getCode();
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T data) {
        return of(ErrorCode.OK, data, null);
    }

    public static <T> Response<T> of (ErrorCode code, T data, String msg) {
        Response<T> resp = new Response();
        resp.setData(data);
        resp.setStatus(code);
        resp.setMsg(msg);

        return resp;
    }

    public static <T> Response<T> error(ErrorCode code, String msg) {
        return of(code, null, msg);
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
