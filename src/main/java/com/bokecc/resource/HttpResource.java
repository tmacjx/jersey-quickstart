package com.bokecc.resource;


import com.bokecc.entity.Param;
import com.bokecc.entity.annotation.JerseyRest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Slf4j
@JerseyRest
@Path("/http")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "http-resource", produces = MediaType.APPLICATION_JSON)
public class HttpResource {

    @Autowired
    private RestTemplate restTemplate;

    private static String GET_URL = "baidu.com";
    private static String POST_URL = "baidu.com";

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get请求", httpMethod = "GET")
    public void get(
            @ApiParam(required = true, value = "用户id")
            @NotBlank
            @QueryParam("userid") String userId){

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> getParameters = new LinkedMultiValueMap<String, String>();
        getParameters.add("userid", userId);

        HttpEntity<MultiValueMap<String, String>> requestEntity
                = new HttpEntity<MultiValueMap<String, String>>(getParameters, headers);
    }





}
