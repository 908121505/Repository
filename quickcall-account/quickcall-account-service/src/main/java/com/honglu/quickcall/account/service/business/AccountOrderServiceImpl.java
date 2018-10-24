package com.honglu.quickcall.account.service.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.IAccountOrderService;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：对外订单查询接口
 * @Package: com.honglu.quickcall.account.service.business 
 * @author: chenliuguang   
 * @date: 2018年10月24日 下午1:54:25
 */
@Service("Account.AccountOrderService")
public class AccountOrderServiceImpl implements IAccountOrderService {
	private static final Logger logger = LoggerFactory.getLogger(AccountOrderServiceImpl.class);

	@Override
	public Integer checkReceiveOrderByCustomerSkillId(Long customerId, Long custSkillId) {
		return null;
	}

	
}
