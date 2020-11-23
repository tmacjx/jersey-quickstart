package com.bokecc.exception;

import com.bokecc.supports.ResultCode;

public class UserException extends BaseException{

    public UserException(ResultCode result){
        super(result);
    }

    public UserException(Integer code, String message){
        super(code, message);
    }

}
