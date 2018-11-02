package com.honglu.quickcall.producer.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author xiangping
 * @date 2018-10-30 15:49
 */
@SpringBootApplication(scanBasePackages = {"com.honglu.quickcall.producer.core"})
@ImportResource({"classpath:config/spring-dubbo.xml"})
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}