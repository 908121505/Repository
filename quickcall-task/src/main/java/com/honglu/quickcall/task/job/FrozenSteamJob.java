package com.honglu.quickcall.task.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.task.dao.AccountMapper;
import com.honglu.quickcall.task.dao.TradeDetailMapper;
import com.honglu.quickcall.task.entity.Account;
import com.honglu.quickcall.task.entity.TradeDetail;

/**
 * 冻结金额24小时回账 job
 * 
 * @author liyingtang
 *
 */
@Component
public class FrozenSteamJob {
	public static final Logger logger = LoggerFactory.getLogger(FrozenSteamJob.class);

	/*
	 * @Autowired private AccountService accountService;
	 */

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private TradeDetailMapper tradeDetailMapper;

	@Scheduled(cron = "* 0/10 * * * ?")
	public void execute() {

		logger.info("冻结金额12小时后   到账户越操作 job 开启------------------------");

		List<Account> list = accountMapper.queryFrozenAccouont();
		if (list != null && list.size() > 0) {
			for (Account account : list) {

				String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + account.getCustomerId();

				String userFrozenValue = JedisUtil.get(userFrozenkey);
				if (StringUtils.isNotBlank(userFrozenValue)) {
					List<String> strList = new ArrayList<String>();
					String[] arys = userFrozenValue.split(",");
					for (int i = 0; i < arys.length; i++) {
						String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_STREAM + arys[i];
						String frozenTimeKey = RedisKeyConstants.ACCOUNT_USERFROZEN_Time + arys[i];
						String stramValue = JedisUtil.get(steamFrozenKey);
						String frozenTimeValue = JedisUtil.get(frozenTimeKey);
						String frozenOrderNo = JedisUtil.get(RedisKeyConstants.ACCOUNT_USERFROZEN_ORDER_NO + arys[i]);
						if (StringUtils.isNotBlank(stramValue)) {
							if (StringUtils.isEmpty(frozenTimeValue)) {
								// 操作流水
								if (account.getFrozenAmounts().compareTo(new BigDecimal(stramValue)) == -1) {
									logger.info("冻结金额到账异常-----流水金额大于冻结金额， 流水Id:" + arys[i]);
									continue;
								}

								// 冻结金额出账
								accountMapper.outAccount(account.getCustomerId(), new BigDecimal(stramValue),
										TransferTypeEnum.FROZEN.getType());
								// 记录流水
								TradeDetail tradeDetail = new TradeDetail();
								// 流水Id
								if (StringUtils.isNotBlank(frozenOrderNo)) {
									tradeDetail.setOrderNo(Long.parseLong(frozenOrderNo));
								}
								tradeDetail.setTradeId(UUIDUtils.getId());
								tradeDetail.setCustomerId(account.getCustomerId());
								tradeDetail.setCreateTime(new Date());
								tradeDetail.setType(AccountBusinessTypeEnum.FroZenAccount.getType());
								tradeDetail.setOutPrice(new BigDecimal(stramValue));
								tradeDetailMapper.insertSelective(tradeDetail);
								// 记录操过12小时的冻结流水Id
								strList.add(arys[i]);
							}
						}
					}
					// 数组移除操过12小时的冻结流水Id
					for (int i = 0; i < strList.size(); i++) {
						arys = remove(arys, strList.get(i));
					}
					userFrozenValue = StringUtils.join(arys, ",");
					if (userFrozenValue.length() > 0) {
						JedisUtil.set(userFrozenkey, userFrozenValue);
					} else {
						JedisUtil.del(userFrozenkey);
					}
				} else {
					logger.error("订单异常,不存在冻结金额流水 异常用户Id:" + account.getCustomerId());

				}

			}
		}

		logger.info("冻结金额12小时后   到账户越操作 job 结束------------------------");

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
