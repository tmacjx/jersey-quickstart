package com.bokecc;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bokecc.mapper")
@ServletComponentScan(basePackages = {"com.bokecc"})
public class Application implements CommandLineRunner
{
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info(":::#####################################################################");

        log.info(":::Started successfully!");

        log.info(":::#####################################################################");
    }
}
