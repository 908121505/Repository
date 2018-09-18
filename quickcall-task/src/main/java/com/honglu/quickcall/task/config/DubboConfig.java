package com.honglu.quickcall.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource({"classpath:applicationContext-dubbo-consumer.xml"})
public class DubboConfig {

}
