package com.bokecc.supports;


public interface IResultCode<C>{
    C getCode();
    String getMessage();
}