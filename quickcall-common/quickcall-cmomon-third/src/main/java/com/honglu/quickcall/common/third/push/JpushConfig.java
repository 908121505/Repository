package com.honglu.quickcall.common.third.push;

import java.util.ResourceBundle;

/**
 * 读JPush的配置信息
 *
 */
public class JpushConfig {
	
	 public static String appKey = ResourceBundle.getBundle("thirdconfig").getString("JPush_key");
	 
	 public static String masterSecret = ResourceBundle.getBundle("thirdconfig").getString("JPush_masterSecret");
}
