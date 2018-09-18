package com.calf.cn.utils;

import java.util.Date;

/**
 * 验证是否为空
 * 
 * @author Eros
 * 
 */
public class Verifyempty {

	/**
	 * 功能描述：判断对象是否为空或空串，如果为空则返回“true”，不为空返回“false”
	 */
	public static boolean isEmpty(String object) {
		// 如果为空或空字符串
		if (object == null || object.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 功能描述：判断对象是否为空或空串，如果为空则返回“true”，不为空返回“false”
	 */
	public static boolean isEmpty(Object object) {
		// 如果为空或空字符串
		if (object == null) {
			return true;
		} else {
			return isEmpty(object.toString());
		}
	}

	/**
	 * 功能描述：判断对象是否为空或空串，如果为空则返回“true”，不为空返回“false”
	 */
	public static boolean isEmpty(Date object) {
		if (object == null)
			return true;
		return false;
	}
}
