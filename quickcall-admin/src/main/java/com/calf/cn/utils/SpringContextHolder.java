package com.calf.cn.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * 以静态变量保存Spring ApplicationContext, 
 * 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * Copyright (c) 2005-2011 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SpringContextHolder.java 1468 2011-02-10 15:22:48Z calvinxiu $
 */
@Service
public class SpringContextHolder implements ApplicationContextAware,DisposableBean {
	
	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		SpringContextHolder.applicationContext = arg0;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 
	 * 自动转型为所赋值对象的类型.
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		SpringContextHolder.applicationContext = null;
	}
	
}