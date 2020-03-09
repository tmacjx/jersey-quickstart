package com.bokecc.resource;

import com.bokecc.entity.annotation.JerseyRest;
import com.bokecc.model.User;
import com.bokecc.entity.Response;
import com.bokecc.service.IuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
//import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Slf4j
@JerseyRest
@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "user-resource", produces = MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private IuserService userService;

    @GET
    @Path("/user")
    @ApiOperation(value = "查询user", httpMethod = "GET")
    public Response getUser(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @QueryParam("id") String id){
        User user = userService.selectById(id);
        return Response.ok(user.getId());
    }
}
