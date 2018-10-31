package com.honglu.quickcall.gateway.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiangping
 * @date 2018-10-31 15:21
 */
@SpringBootApplication(scanBasePackages = {"com.honglu.quickcall.gateway.core"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
