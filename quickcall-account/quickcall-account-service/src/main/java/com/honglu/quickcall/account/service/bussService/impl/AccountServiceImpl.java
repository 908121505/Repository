package com.honglu.quickcall.account.service.bussService.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.TradeDetail;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.service.bussService.AccountService;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.account.service.dao.TradeDetailMapper;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;

@Service
public class AccountServiceImpl implements AccountService {

	private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private TradeDetailMapper tradeDetailMapper;

	private final static String froZenTime = ResourceBundle.getBundle("thirdconfig").getString("froZenTime");;

	@Override
	public Account queryAccount(Long customerId) {
		// TODO Auto-generated method stub
		return accountMapper.queryAccount(customerId);
	}

	@Override
	public void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType, Long orderNo) {

		// 入账
		accountMapper.inAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setOrderNo(orderNo);
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setInPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

		if (transferType == TransferTypeEnum.FROZEN) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + customerId;
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_STREAM + tradeDetail.getTradeId();
			String frozenTimeKey = RedisKeyConstants.ACCOUNT_USERFROZEN_Time + tradeDetail.getTradeId();
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue)) {
				userFrozenValue = userFrozenValue + "," + tradeDetail.getTradeId();
				JedisUtil.set(userFrozenkey, userFrozenValue);
			} else {
				JedisUtil.set(userFrozenkey, tradeDetail.getTradeId() + "");

			}
			JedisUtil.set(steamFrozenKey, amount + "");
			// 缓存24小时
			JedisUtil.set(frozenTimeKey, "1", Integer.parseInt(froZenTime));
			// 流水对应的订单Id
			JedisUtil.set(RedisKeyConstants.ACCOUNT_USERFROZEN_ORDER_NO + tradeDetail.getTradeId(), orderNo.toString());

		}

	}

	@Override
	public void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType, Long orderNo) {
		// 入账
		accountMapper.outAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setOrderNo(orderNo);
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setOutPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

	}

}
