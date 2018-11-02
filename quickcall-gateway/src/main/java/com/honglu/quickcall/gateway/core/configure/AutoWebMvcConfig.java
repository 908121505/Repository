package com.honglu.quickcall.gateway.core.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xping.framework.web.interceptor.HttpServletStackInterceptor;
import com.xping.framework.web.interceptor.ParameterInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiangping
 * @date 2018-11-01 10:31
 */

@Configuration
@ComponentScan(basePackages = {"com.xping.framework.web", "com.honglu.quickcall.gateway.**.controller"},
        useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = {Controller.class, RestController.class, ControllerAdvice.class, RestControllerAdvice.class})})
public class AutoWebMvcConfig implements WebMvcConfigurer {

    @Resource(name = "signatureInterceptor")
    private HandlerInterceptor signatureInterceptor;

    @Autowired
    private AccessProperties accessProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpServletStackInterceptor()).order(1);
        registry.addInterceptor(new ParameterInterceptor()).order(2);
        registry.addInterceptor(signatureInterceptor).excludePathPatterns(accessProperties.getNoneCheckHeaderList()).order(3);
        //registry.addInterceptor(authenticationInterceptor).excludePathPatterns(accessProperties.getIgnoreTokenUriList()).order(4);
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        Map<String, String> validationProperties = new HashMap<>();
        validationProperties.put("hibernate.validator.fail_fast", "true");
        localValidatorFactoryBean.setValidationPropertyMap(validationProperties);
        return localValidatorFactoryBean;
    }

    @Bean
    @Primary
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /*// 不显示为null的字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);*/
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return mappingJackson2HttpMessageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, mappingJackson2HttpMessageConverter());
    }
}
