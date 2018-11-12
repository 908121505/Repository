package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description: 修改年龄
 *
 * @author chenpeng
 * @date 2018/10/18 11:31
 */
public class UpdateBirthdayReq extends UserCenterRequest{
	private Long customerId;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
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
		return UserFunctionType.updateBirthday;
	}

}
