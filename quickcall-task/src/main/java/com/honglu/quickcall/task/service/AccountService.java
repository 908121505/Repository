package com.honglu.quickcall.task.service;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;

public interface AccountService {

	/**
	 * 入账
	 * 
	 * @param customerId
	 * @param amount
	 * @param transferType
	 * @param accountBusinessType
	 */
	void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType);

	/**
	 * 入账
	 * 
	 * @param customerId
	 * @param amount
	 * @param transferType
	 * @param accountBusinessType
	 */
	void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType);
}
