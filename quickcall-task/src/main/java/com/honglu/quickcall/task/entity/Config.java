package com.honglu.quickcall.task.entity;

import javax.persistence.Entity;

/**
 *	配置类
 */

public class Config {
	
	private Integer id;
	
	private Integer config_key;//名
	
	private Integer config_value;//值
	
	private String config_postscript;//备注
	
	public Config(){}

	public Config(Integer id, Integer configKey, Integer configValue,
			String configPostscript) {
		super();
		this.id = id;
		config_key = configKey;
		config_value = configValue;
		config_postscript = configPostscript;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConfig_key() {
		return config_key;
	}

	public void setConfig_key(Integer configKey) {
		config_key = configKey;
	}

	public Integer getConfig_value() {
		return config_value;
	}

	public void setConfig_value(Integer configValue) {
		config_value = configValue;
	}

	public String getConfig_postscript() {
		return config_postscript;
	}

	public void setConfig_postscript(String configPostscript) {
		config_postscript = configPostscript;
	}

	@Override
	public String toString() {
		return "Config [config_key=" + config_key + ", config_postscript="
				+ config_postscript + ", config_value=" + config_value
				+ ", id=" + id + "]";
	}
	
}
