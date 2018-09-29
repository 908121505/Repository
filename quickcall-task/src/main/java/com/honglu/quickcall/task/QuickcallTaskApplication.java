package com.honglu.quickcall.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.honglu.quickcall.task.dao")
public class QuickcallTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickcallTaskApplication.class, args);
	}
}
