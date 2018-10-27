package com.honglu.quickcall.task.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.task.service.AccountService;
import com.honglu.quickcall.task.service.IAccountOrderService;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：对外订单查询接口
 * 
 * @Package: com.honglu.quickcall.account.service.business
 * @author: chenliuguang
 * @date: 2018年10月24日 下午1:54:25
 */
@Service("accountOrderService")
public class AccountOrderServiceImpl implements IAccountOrderService {


	@Autowired
	private AccountService accountService;


	@Override
	public void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {
		accountService.inAccount(customerId, amount, transferType, accountBusinessType);

	}

	@Override
	public void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {
		accountService.outAccount(customerId, amount, transferType, accountBusinessType);

	}

}
