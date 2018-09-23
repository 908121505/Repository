package com.honglu.quickcall.user.facade.exchange.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;
/**
 * 
 * @author liuyinkai
 *
 */
public class SaveBirthRequest extends UserCenterRequest {
	private Long customerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday; 
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.SaveBirthday;
	}

}
