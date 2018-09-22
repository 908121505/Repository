package com.honglu.quickcall.user.service.service;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.Interest;

public interface CustomerService extends AbstractService<Customer> {
	//根据id查找用户
	Customer selectByCustomerId(Long accountId);
	
}
