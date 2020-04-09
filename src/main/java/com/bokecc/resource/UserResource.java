package com.bokecc.resource;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bokecc.entity.annotation.JerseyRest;
import com.bokecc.model.User;
import com.bokecc.service.IuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
//import javax.validation.constraints.NotBlank;
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
    private IuserService userService;

    @GET
    @Path("/{id}")
    @ApiOperation(value = "查询user", httpMethod = "GET")
    public User getUser(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @PathParam("id") String id){
        System.out.println(("----- selectById ------"));
        try{
            User user = userService.selectById(Long.valueOf(id));
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @ApiOperation(value = "查询user列表", httpMethod = "GET")
    public List<User> getUsers(){
        // 返回list
        System.out.println(("----- selectAll ------"));
        List<User> userList = userService.selectAll();
        userList.forEach(System.out::println);
        return userList;
    }

    @POST
    @ApiOperation(value = "新增user", httpMethod = "POST")
    public Integer addUser(
            @Valid UserParam userParam
            ){
        System.out.println(("----- insertOne ------"));
        log.info("调度接口参数--> {}", JSON.toJSONString(userParam));
        String userid = userParam.getUserid();
        String username = userParam.getUsername();
        User user = new User();
        user.setUserId(userid);
        user.setUserName(username);
        return userService.insertOne(user);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "更新user", httpMethod = "PUT")
    public Integer updateUser(
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
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where("id", Long.valueOf(id));
        return userService.updateOne(user, ew);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "删除user", httpMethod = "DELETE")
    public Integer deleteUser(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @PathParam("id") String id){
        System.out.println(("----- deleteOne ------"));
        try {
            Integer success = userService.deleteOne(Long.valueOf(id));
            return success;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
