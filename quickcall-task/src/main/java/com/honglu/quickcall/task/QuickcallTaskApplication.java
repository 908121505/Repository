package com.honglu.quickcall.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.honglu.quickcall.task.job"})
public class QuickcallTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickcallTaskApplication.class, args);
    }
}
