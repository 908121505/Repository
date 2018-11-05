package com.honglu.quickcall.account.service.bussService;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;

public interface AccountService {
	/**
	 * 查询账户
	 * 
	 * @param request
	 * @return
	 */
	Account queryAccount(Long customerId);

	/**
	 * 入账
	 * 
	 * @param customerId
	 * @param amount
	 * @param transferType
	 * @param accountBusinessType
	 * @param orderNo
	 *            订单Id 选填
	 */
	void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType, Long orderNo);

	/**
	 * 入账
	 * 
	 * @param customerId
	 * @param amount
	 * @param transferType
	 * @param accountBusinessType
	 * @param orderNo
	 *            订单Id 选填
	 */
	void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType, Long orderNo);
}
