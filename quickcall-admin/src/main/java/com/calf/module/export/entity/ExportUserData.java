package com.calf.module.export.entity;

import org.apache.commons.lang.StringUtils;

public class ExportUserData {
	private String app_code;//项目标识（appid），北京发放
	private String uid;//用户Id，对应customer_info表id
	private String serial_number;//硬件序列号，ott可以是mac地址，手机序列号，有必填但可为空
	private String stu_no;//学号，有必填但可为空
	private String tv_code;//电视号，有必填但可为空
	private String names;//用户名称，有必填但可为空
	private String sex;//用户性别，有必填但可为空
	private String birthday;//用户生日，有必填但可为空
	private String phone;//手机号，有必填但可为空
	private String register_time;//注册时间
	private String email;//邮箱，有必填但可为空
	private String password;//密码，有必填但可为空
	
	public String getApp_code() {
		return app_code;
	}
	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}
	public String getTv_code() {
		return tv_code;
	}
	public void setTv_code(String tv_code) {
		this.tv_code = tv_code;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(serial_number))
			serial_number = "\\N";
		if(StringUtils.isBlank(stu_no))
			stu_no = "\\N";
		if(StringUtils.isBlank(tv_code))
			tv_code = "\\N";
		if(StringUtils.isBlank(names))
			names = "\\N";
		if(StringUtils.isBlank(sex))
			sex = "\\N";
		if(StringUtils.isBlank(birthday))
			birthday = "\\N";
		if(StringUtils.isBlank(register_time))
			register_time = "\\N";
		
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\r\n", 
				uid,serial_number,stu_no,tv_code,names,sex,birthday,phone,register_time,email,app_code,password);
	}
}
