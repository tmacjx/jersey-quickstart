package com.bokecc.config;

import com.bokecc.constant.Constant;
import com.bokecc.supports.RestResponse;
import com.bokecc.supports.ResultCode;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 封装 参数检查异常处理
 *
 */
@Provider
@Slf4j
public class ViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {

        RestResponse re = RestResponse.ofStatus(ResultCode.PARAM_INVALID, prepareMessage(exception));

        return Response.status(Constant.ERROR_HTTP_CODE)
                .entity(re)
                .type("application/json")
                .build();
    }

    private String prepareMessage(ConstraintViolationException exception) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            // msg+=cv.getPropertyPath()+" "+cv.getMessage()+"\n";
            sb.append(cv.getMessage());
        }
        return sb.toString();
    }
}
