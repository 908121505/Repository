package com.honglu.quickcall.common.third.mob;

public class MobMsg {
      private String tel;
      private String code;
      private Integer type;//1发送 2验证
      
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
