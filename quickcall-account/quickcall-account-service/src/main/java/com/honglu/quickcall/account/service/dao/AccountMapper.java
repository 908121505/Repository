package com.honglu.quickcall.account.service.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.Account;

public interface AccountMapper {
	int deleteByPrimaryKey(Long accountId);

	int insert(Account record);

	int insertSelective(Account record);

	Account selectByPrimaryKey(Long accountId);

	int updateByPrimaryKeySelective(Account record);

	int updateByPrimaryKey(Account record);

	void createUserAccount(Account userAccount);

	Account queryAccount(@Param("userId") Long customerId);

	/**
	 * 入账
	 * 
	 * @param customerId
	 * @param inMoney
	 * @param type
	 *            1 +可用金额 2 +提现金额
	 */
	void inAccount(@Param("userId") Long customerId, @Param("amount") BigDecimal inMoney, @Param("type") Integer type);

	/**
	 * 出账
	 * 
	 * @param customerId
	 * @param outMoney
	 * @param type
	 *            1 -可用金额 2 -提现金额
	 */
	void outAccount(@Param("userId") Long customerId, @Param("amount") BigDecimal outMoney,
			@Param("type") Integer type);

}