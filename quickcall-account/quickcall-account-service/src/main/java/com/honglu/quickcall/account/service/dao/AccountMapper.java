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
	
	Account queryAccount(@Param("userId")Long customerId);
	
	void inAccount(@Param("userId")Long customerId,@Param("amount")BigDecimal inMoney);
	
	void outAccount(@Param("userId")Long customerId,@Param("amount")BigDecimal outMoney);
	
	
}