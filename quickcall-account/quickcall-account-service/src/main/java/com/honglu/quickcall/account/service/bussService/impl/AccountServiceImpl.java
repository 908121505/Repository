package com.honglu.quickcall.account.service.bussService.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setInPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

		if (transferType == TransferTypeEnum.FROZEN) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + customerId;
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + tradeDetail.getTradeId();
			String frozenTimeKey = RedisKeyConstants.ACCOUNT_USERFROZEN_Time + tradeDetail.getTradeId();
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue)) {
				userFrozenValue = userFrozenValue + "," + tradeDetail.getTradeId();
			} else {
				JedisUtil.set(userFrozenkey, tradeDetail.getTradeId() + "");

			}
			JedisUtil.set(steamFrozenKey, amount + "");
			// 缓存24小时
			JedisUtil.set(frozenTimeKey, "1", 86400);

		}

	}

	@Override
	public void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {
		// 入账
		accountMapper.outAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setOutPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

		if (transferType == TransferTypeEnum.FROZEN) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + customerId;

			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue)) {
				List<String> strList = new ArrayList<String>();
				String[] arys = userFrozenValue.split(",");
				for (int i = 0; i < arys.length; i++) {
					String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + arys[i];
					String frozenTimeKey = RedisKeyConstants.ACCOUNT_USERFROZEN_Time + arys[i];
					String stramValue = JedisUtil.get(steamFrozenKey);
					String frozenTimeValue = JedisUtil.get(frozenTimeKey);
					if (StringUtils.isNotBlank(stramValue)) {
						if (StringUtils.isEmpty(frozenTimeValue)) {
							// 操作流水

							// 记录操过24小时的冻结流水Id
							strList.add(stramValue);
						}
					}
				}
				// 数组移除操过24小时的冻结流水Id
				for (int i = 0; i < strList.size(); i++) {
					arys = remove(arys, strList.get(i));
				}
				userFrozenValue = StringUtils.join(arys, ",");
				if (userFrozenValue.length() > 0) {
					JedisUtil.set(userFrozenkey, userFrozenValue);
				}
			} else {
				logger.error("订单异常,不存在冻结金额流水");

			}

		}

	}

	private static String[] remove(String[] arr, String str) {
		String[] tmp = new String[arr.length - 1];
		int idx = 0;
		boolean hasRemove = false;
		for (int i = 0; i < arr.length; i++) {

			if (!hasRemove && arr[i] == str) {
				hasRemove = true;
				continue;
			}

			tmp[idx++] = arr[i];
		}

		return tmp;
	}

}
