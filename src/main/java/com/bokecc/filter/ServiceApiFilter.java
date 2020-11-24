package com.bokecc.filter;

import com.bokecc.config.SpringContextHolder;
import com.bokecc.service.ICommonService;
import com.bokecc.supports.RestResponse;
import com.bokecc.supports.ResultCode;
import com.bokecc.util.JwtPaire;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.util.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 *
 * 服务端间调用JWT鉴权
 **/

@Slf4j
public class ServiceApiFilter implements ContainerRequestFilter {

    private static final String FILTER_URL = "/api/service";

    private static final String AUTH_TOKEN = "auth_token";

    private static final String OPTION_METHOD = "OPTIONS";

    public static final String DEFAULT_CHARSET = "utf-8";


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        ContainerRequest request = (ContainerRequest) requestContext.getRequest();

        String url = request.getRequestUri().toString();

        log.debug(url);

        if(request.getMethod().toUpperCase().equals(OPTION_METHOD)){

            return;
        }

        if(null == url || !url.contains(FILTER_URL)){

            return;
        }

        // 请求Query参数中auth_token就是jwt
        String authToken = request.getUriInfo().getQueryParameters().getFirst(AUTH_TOKEN);

        log.info("authToken = {}", authToken);

        if(StringUtils.isEmpty(authToken)){

            log.info("authToken = {}, 认证失败", authToken);

            requestContext.abortWith(
                    Response
                            .status(Response.Status.UNAUTHORIZED)
                            .header("content-type", "application/json;charset=utf-8")
                            .encoding(DEFAULT_CHARSET)
                            .entity(RestResponse.ofStatus(ResultCode.AUTH_FAILURE))
                            .build());

        }

        // TODO 请求JWT服务 进行验证
//        JwtPaire jwtPaire = auth(authToken);
//
//        if(jwtPaire.getLeft() != 0){
//
//            log.info("code = {}, msg= {}", jwtPaire.getLeft(), jwtPaire.getRight());
//
//            requestContext.abortWith(
//                    Response
//                            .status(Response.Status.UNAUTHORIZED)
//                            .header("content-type", "application/json;charset=utf-8")
//                            .encoding(DEFAULT_CHARSET)
//                            .entity(RestResponse.ofStatus(ResultCode.AUTH_FAILURE))
//                            .build());
//
//            return;
//
//        }
//
//        log.debug("认证通过");

    }

//    private JwtPaire auth(String ccToken){
//
//        ICommonService commonService = SpringContextHolder.getBean(ICommonService.class);
//
//        return commonService.authJwt(ccToken);
//    }
}
