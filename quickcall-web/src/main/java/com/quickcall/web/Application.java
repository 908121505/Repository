package com.quickcall.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author xiangping
 * @date 2018-10-30 15:49
 */
@SpringBootApplication(scanBasePackages = {"com.quickcall.web"})
@ImportResource({"classpath:config/spring-dubbo.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}