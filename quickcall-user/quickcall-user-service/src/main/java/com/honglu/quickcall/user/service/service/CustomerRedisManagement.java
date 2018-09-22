package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.user.facade.entity.Customer;

public interface CustomerRedisManagement {
	/**
	 * 获取redis用户
	 * @param customerId
	 * @return
	 */
	public Customer getCustomer(Long customerId);
}
