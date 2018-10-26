package com.calf.cn.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBeanUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(MyBeanUtil.class);
	public static HashMap<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
 
					map.put(key, value);
				}
 
			}
		} catch (Exception e) {
			LOGGER.error("transBean2Map Error {}" ,e);
		}
		return map;
 
	}
}
