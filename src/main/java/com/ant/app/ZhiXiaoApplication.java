package com.ant.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.ant.app.dao")
public class ZhiXiaoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ZhiXiaoApplication.class, args);
    }
}
