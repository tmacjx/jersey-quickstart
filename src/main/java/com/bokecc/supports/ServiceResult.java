package com.bokecc.supports;


import java.util.Objects;


public class ServiceResult<T, C> {

    private static final IResultCode<Integer> SUCCESS = ResultCode.OK;

    private IResultCode<C> resultCode;

    private T res;
    private boolean isSuccess;

    public ServiceResult(IResultCode<C> resultCode, boolean isSuccess, T res) {
        this.resultCode = resultCode;
        this.isSuccess = isSuccess;
        this.res = res;
    }

    public boolean isSuccess() {
        return isSuccess;
    }


    public ServiceResult<T, C> resultCode(IResultCode<C> resultCode){

        this.setResultCode(resultCode);

        return this;
    }


    public ServiceResult<T, C> res(T res){

        this.setRes(res);
        return this;
    }

    public ResultCode getResultCode() {
        return (ResultCode) resultCode;
    }

    public void setResultCode(IResultCode<C> resultCode) {
        this.resultCode = resultCode;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }


    static <D, C> ServiceResultBuilder<D, C> builder() {
        return new ServiceResultBuilder<>();
    }

    public static <T> ServiceResult<T, Integer> ofSuccess(T data) {
        ServiceResultBuilder<T, Integer> builder = builder();
        builder.isSuccess(true).code(SUCCESS.getCode()).message(SUCCESS.getMessage());
        return builder.data(data).build();
    }

    public static <T> ServiceResult<T, Integer> ofSuccess() {
        ServiceResultBuilder<T, Integer> builder = builder();
        builder.isSuccess(true).code(SUCCESS.getCode()).message(SUCCESS.getMessage());
        return builder.build();
    }

    public static <T> ServiceResult<T, Integer> ofException(IResultCode<Integer> resultCode) {
        ServiceResultBuilder<T, Integer> builder = builder();
        builder.isSuccess(false).code(resultCode.getCode()).message(resultCode.getMessage());
        return builder.build();
    }


    public static class DefaultMessage<C> implements IResultCode<C>, java.io.Serializable {

        private C code;
        private String message;

        public DefaultMessage(C code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public C getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "DefaultMessage{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }


    public static class ServiceResultBuilder<T, C> {

        private T data;
        private C code;
        private String message;
        private boolean isSuccess;

        ServiceResultBuilder() { //package private
        }

        public ServiceResultBuilder<T, C> data(T data) {
            this.data = data;
            return this;
        }

        ServiceResultBuilder<T, C> isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }


        public ServiceResultBuilder<T, C> code(C code) {
            this.code = code;
            return this;
        }

        public ServiceResultBuilder<T, C> message(String message) {
            this.message = message;
            return this;
        }
        public ServiceResult<T, C> build() {
            Objects.requireNonNull(code, "code");
            Objects.requireNonNull(code, "message");
            return new ServiceResult<T, C>(new DefaultMessage<C>(code, message), isSuccess, data);
        }
    }
}
