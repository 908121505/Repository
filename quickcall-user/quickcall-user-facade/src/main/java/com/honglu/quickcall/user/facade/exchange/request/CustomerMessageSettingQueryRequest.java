package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class CustomerMessageSettingQueryRequest extends UserCenterRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2921184139238424574L;
	
	/**
     * 登录用户id
     */
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
