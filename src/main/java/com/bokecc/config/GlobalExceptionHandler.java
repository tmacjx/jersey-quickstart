package com.bokecc.config;

import com.bokecc.supports.RestResponse;
import com.bokecc.supports.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *  全局异常处理
 */
@Slf4j
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        log.error("未处理的异常！{}", exception.getClass().getName(), exception);

        String sb = exception.getClass().getSimpleName() +
                " --> " +
                exception.getMessage();
        RestResponse res = new RestResponse(CommonErrorCode.UNKNOWN_ERROR.getCode(), sb, null);
        return Response.ok(res, MediaType.APPLICATION_JSON_TYPE).status(500).build();
    }
}
