package com.bokecc.resource;

import com.bokecc.entity.annotation.JerseyRest;
import com.bokecc.param.JwtParam;
import com.bokecc.service.IJwtService;
import com.bokecc.supports.RestResponse;
import com.bokecc.supports.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author: Faizel Lannister(chufuying)
 * @date: 2020/5/28
 * @version: 0.0.1
 * @jdk: 1.8
 * @email: chufuying@163.com
 **/

@Slf4j
@JerseyRest
@Path("/service/jwt")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "jwt-resource", produces = MediaType.APPLICATION_JSON)
public class JwtResource {

    @Autowired
    private IJwtService jwtService;

    @GET
    @Path("/find_all")
    @ApiOperation(value="查询所有jwt",httpMethod="GET")
    public RestResponse findAll(
            @NotBlank(message = "auth_token 不能为null或者空")
            @ApiParam(value = "认证token", required = true)
            @QueryParam("auth_token")
            String authToken
    ){

        return RestResponse.ofSuccess(jwtService.findAll());
    }

    @DELETE
    @Path("/delete")
    @ApiOperation(value="删除指定的jwt",httpMethod="DELETE")
    public RestResponse deleteByKey(
            @NotBlank(message = "auth_token 不能为null或者空")
            @ApiParam(value = "认证token", required = true)
            @QueryParam("auth_token")
            String authToken,
            @NotBlank(message = "jwt_key 不能为null或者空")
            @ApiParam(value = "分配密钥的key", required = true)
            @QueryParam("jwt_key")
            String jwtKey){

        int re = jwtService.delete(jwtKey);

        if(re > 0){

            return RestResponse.ofSuccess(re);
        }

        return RestResponse.ofFail();
    }

    @POST
    @Path("/save")
    @ApiOperation(value="保存",httpMethod="POST")
    public RestResponse insert(
            @NotBlank(message = "auth_token 不能为null或者空")
            @ApiParam(value = "认证token", required = true)
            @QueryParam("auth_token")
            String authToken,
            @Valid JwtParam param){

        ResponseParam<Integer> responseParam = jwtService.insert(param);

        ErrorCode errorCode = responseParam.getErrorCode();

        if(errorCode.getCode() == 0){

            return Response.ok(responseParam.getRes());
        }

        return Response.error(errorCode);
    }

    @PUT
    @Path("/update")
    @ApiOperation(value="更新",httpMethod="PUT")
    public RestResponse updateByKey(
            @NotBlank(message = "auth_token 不能为null或者空")
            @ApiParam(value = "认证token", required = true)
            @QueryParam("auth_token") String authToken,
            @Valid JwtParam param
    ){

        int re = jwtService.update(param);

        if(re > 0){

            return RestResponse.ofSuccess(re);
        }

        return RestResponse.ofFail();
    }
}
