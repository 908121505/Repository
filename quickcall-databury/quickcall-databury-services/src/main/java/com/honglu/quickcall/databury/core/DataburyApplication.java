package com.honglu.quickcall.databury.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * @author xiangping
 * @date 2018-10-30 18:42
 */
@SpringBootApplication(scanBasePackages = {"com.honglu.quickcall.databury.core"})
@ImportResource({"classpath:config/spring-dubbo.xml"})
public class DataburyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataburyApplication.class, args);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
