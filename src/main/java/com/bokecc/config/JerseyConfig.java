package com.bokecc.config;

import com.bokecc.entity.annotation.JerseyRest;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import io.swagger.jaxrs.config.BeanConfig;
import com.bokecc.resource.UserResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.ApplicationPath;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import javax.annotation.PostConstruct;


@Configuration
@Slf4j
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig{

    @Autowired
    private ApplicationContext applicationContext;

    public JerseyConfig() {
    }

    @PostConstruct
    private void init(){
        // this.register(UserResource.class);

        this.register(GlobalExceptionHandler.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // 寻找所有JerseyRest注解的Resources
        Map<String, Object> resources = applicationContext.getBeansWithAnnotation(JerseyRest.class);

        if(resources.isEmpty()){

            return;
        }

        resources.forEach((x, y) ->{

            this.register(y.getClass());
        });

        // 配置Swagger文档
        configureSwagger();

    }
    private void configureSwagger() {

        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("eagle-eye");
        config.setTitle("API 文档");
        config.setSchemes(new String[] { "http"});
        config.setBasePath("/api");
        config.setResourcePackage("com.bokecc.resource");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
