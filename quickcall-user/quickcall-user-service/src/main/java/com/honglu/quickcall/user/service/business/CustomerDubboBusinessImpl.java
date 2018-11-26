package com.honglu.quickcall.user.service.business;

import com.honglu.quickcall.user.facade.business.CustomerDubboBusiness;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.service.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("User.CustomerDubboBusiness")
public class CustomerDubboBusinessImpl implements CustomerDubboBusiness {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDubboBusinessImpl.class);

	@Autowired
	private CustomerService customerService;


	@Override
	public Customer queryCustomerByCustomerId(Long customerId) {
		return customerService.queryCustomerByCustomerId(customerId);
	}

	@Override
	public CustomerSkill queryCustomerSkillByCustomerId(Long customerId) {
		return customerService.queryCustomerSkillByCustomerId(customerId);
	}
}
