package com.bokecc.resource;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bokecc.entity.annotation.JerseyRest;
import com.bokecc.model.User;
import com.bokecc.service.IUserService;
import com.bokecc.supports.RestResponse;
import com.bokecc.supports.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import com.bokecc.param.UserParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@JerseyRest
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "user-resource", produces = MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private IUserService userService;

    @GET
    @Path("/{id}")
    @ApiOperation(value = "查询user", httpMethod = "GET")
    public RestResponse getUser(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @PathParam("id") String id){
        System.out.println(("----- selectById ------"));
        User user = userService.selectByUserId(id);
        if(user != null){
            return RestResponse.ofSuccess(user);
        }
        return RestResponse.ofFail();
    }

    @GET
    @ApiOperation(value = "查询user列表", httpMethod = "GET")
    public RestResponse getUsers(){
        // 返回list
        System.out.println(("----- selectAll ------"));
        List<User> userList = userService.selectAll();
        if(userList != null){
            return RestResponse.ofSuccess(userList);
        }
        return RestResponse.ofFail();
    }

    @POST
    @ApiOperation(value = "新增user", httpMethod = "POST")
    public RestResponse addUser(
            @Valid UserParam userParam
            ){
        System.out.println(("----- insertOne ------"));
        log.info("调度接口参数--> {}", JSON.toJSONString(userParam));
        String userId = userParam.getUserId();
        String username = userParam.getUsername();
        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        int res = userService.insertOne(user);
        if(res == 1){
            return RestResponse.ofSuccess();
        }
        return RestResponse.ofFail();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "更新user", httpMethod = "PUT")
    public RestResponse updateUser(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @PathParam("id") String id,
            @Valid UserParam userParam
    ) {
        System.out.println(("----- updateOne ------"));
        log.info("调度接口参数--> {}", JSON.toJSONString(userParam));

        User user = new User();
        user.setUserName(userParam.getUsername());

        //设置查询条件
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.where("id", Long.valueOf(id));
        int res = userService.updateOne(user, wrapper);
        if(res == 1){
            return RestResponse.ofSuccess();
        }
        return RestResponse.ofFail();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "删除user", httpMethod = "DELETE")
    public RestResponse deleteUser(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @PathParam("id") String id) {
        System.out.println(("----- deleteOne ------"));
        int res = userService.deleteOne(Long.valueOf(id));
        if( res == 1){
            return RestResponse.ofSuccess();
        }
        return RestResponse.ofFail();
    }

}
