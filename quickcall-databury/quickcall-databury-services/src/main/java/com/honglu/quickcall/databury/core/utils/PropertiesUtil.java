package com.honglu.quickcall.databury.core.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil cfg = null;
	private static Properties properties = new Properties();
	static {
		InputStream in = PropertiesUtil.class.getResourceAsStream("/config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {

		}
	}

	public static PropertiesUtil getInstance() {
		synchronized (PropertiesUtil.class) {
			if (cfg == null) {
				cfg = new PropertiesUtil();
			}
		}
		return cfg;
	}
	public static String get(String key) {
		return properties.getProperty(key).trim();
	}
}
