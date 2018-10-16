package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.TradeDetail;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.account.service.dao.TradeDetailMapper;
import com.honglu.quickcall.account.service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private TradeDetailMapper tradeDetailMapper;

	@Override
	public Account queryAccount(Long customerId) {
		// TODO Auto-generated method stub
		return accountMapper.queryAccount(customerId);
	}

	@Override
	public void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {

		// 入账
		accountMapper.inAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setSellerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

	}

	@Override
	public void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {
		// 入账
		accountMapper.outAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setSellerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

	}

}
