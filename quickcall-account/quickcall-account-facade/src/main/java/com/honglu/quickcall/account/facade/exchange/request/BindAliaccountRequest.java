package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class BindAliaccountRequest extends AbstractRequest {
	/**
	* 
	*/
	private static final long serialVersionUID = 7522833446729843390L;
	private String customerId;// '用户id',
	private String account;// '支付宝账号【邮箱或手机号】',
	private String realname;// '支付宝账户实名认证真实姓名',
	private Integer etype;// 1查看支付宝账号，2或不传 是绑定支付宝

	public Integer getEtype() {
		return etype;
	}

	public void setEtype(Integer etype) {
		this.etype = etype;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.BindAliaccount;
	}

}
