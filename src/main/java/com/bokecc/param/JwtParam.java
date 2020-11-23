package com.bokecc.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: Faizel Lannister(chufuying)
 * @date: 2020/5/28
 * @version: 0.0.1
 * @jdk: 1.8
 * @email: chufuying@163.com
 **/

@Data
public class JwtParam {

    @NotBlank(message = "app_key 不能为空或者null")
    @ApiModelProperty(required = true, dataType = "String", value = "认证key")
    @JsonProperty("app_key")
    private String appKey;

    @NotBlank(message = "app_secret 不能为空或者null")
    @ApiModelProperty(required = true, dataType = "String", value = "认证k密钥")
    @JsonProperty("app_secret")
    private String appSecret;

    @ApiModelProperty(required = false, dataType = "String", value = "密钥归属部分")
    @JsonProperty("department")
    private String department;

    @ApiModelProperty(required = false, dataType = "String", value = "密钥归属的服务")
    @JsonProperty("service_name")
    private String serviceName;

    @ApiModelProperty(required = false, dataType = "String", value = "备注")
    @JsonProperty("remark")
    private String remark;
}
