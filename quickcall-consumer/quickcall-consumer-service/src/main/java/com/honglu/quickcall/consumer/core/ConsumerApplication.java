package com.honglu.quickcall.consumer.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author xiangping
 * @date 2018-10-30 15:49
 */
@SpringBootApplication(scanBasePackages = {"com.honglu.quickcall.consumer.core"})
@ImportResource({"classpath:config/spring-dubbo.xml"})
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}