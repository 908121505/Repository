package com.calf.module.system.entity;

public class SysLog {

	private Long id;
	private String account;//操作人
	private String oper_type;//操作类型
	private String oper_url;//请求地址
	private String oper_param;//请求参数	
	private String ip_addr;//IP地址
	private String create_time;//请求时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOper_type() {
		return oper_type;
	}
	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
	public String getOper_url() {
		return oper_url;
	}
	public void setOper_url(String oper_url) {
		this.oper_url = oper_url;
	}
	public String getOper_param() {
		return oper_param;
	}
	public void setOper_param(String oper_param) {
		this.oper_param = oper_param;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public SysLog() {
		super();
	}
	public SysLog(String account, String oper_type, String oper_url,
			String oper_param, String ip_addr, String create_time) {
		super();
		this.account = account;
		this.oper_type = oper_type;
		this.oper_url = oper_url;
		this.oper_param = oper_param;
		this.ip_addr = ip_addr;
		this.create_time = create_time;
	}
	
	
}
