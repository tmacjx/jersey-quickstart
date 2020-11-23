package com.bokecc.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author: Faizel Lannister(chufuying)
 * @date: 2019/9/12
 * @version: 0.0.1
 * @jdk: 1.8
 * @email: chufuying@163.com
 **/
@Configuration
public class PropertiesObtainConfig implements EnvironmentAware {

    public static Environment env;

    @Override
    public void setEnvironment(Environment environment) {

        env = environment;
    }
}
