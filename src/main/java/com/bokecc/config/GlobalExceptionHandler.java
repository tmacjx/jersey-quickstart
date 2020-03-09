package com.bokecc.config;

import com.bokecc.entity.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        log.error("未处理的异常！{}", exception.getClass().getName(), exception);

        StringBuilder sb = new StringBuilder();

        sb.append(exception.getClass().getSimpleName());

        sb.append(" --> ");

        sb.append(exception.getMessage());

        com.bokecc.entity.Response res = com.bokecc.entity.Response.error(
                ErrorCode.UNKNOW_EXCEPTION, sb.toString());

        return Response.ok(res, MediaType.APPLICATION_JSON_TYPE).status(500).build();
    }
}
