package com.bokecc.entity;

public class Param {
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ParamEntity{" +
                "param='" + param + '\'' +
                '}';
    }
}
