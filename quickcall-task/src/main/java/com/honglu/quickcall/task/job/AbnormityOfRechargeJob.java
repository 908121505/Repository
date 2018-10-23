package com.honglu.quickcall.task.job;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.entity.TradeDetail;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.common.api.util.HttpClientUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.task.dao.AccountMapper;
import com.honglu.quickcall.task.dao.RechargeMapper;
import com.honglu.quickcall.task.dao.TradeDetailMapper;
import com.honglu.quickcall.task.entity.Recharge;

/**
 * 充值回调异常修复 job
 * 
 * @author liyingtang
 *
 */
@Component
public class AbnormityOfRechargeJob {
	public static final Logger logger = LoggerFactory.getLogger(AbnormityOfRechargeJob.class);

	@Autowired
	private RechargeMapper rechargeMapper;

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private TradeDetailMapper tradeDetailMapper;

	private static String aliPayOrderQuery = ResourceBundle.getBundle("thirdconfig").getString("ALI_PAY_ORDER_QUERY");

	@Scheduled(cron = "* 0/15 * * * ?")
	// @Scheduled(cron = "0/5 * * * * ?")
	public void execute() {

		logger.info("充值回调异常修复  job 开启------------------------");

		List<Recharge> rechargeList = rechargeMapper.queryAbnormityOfRecharge();
		for (int i = 0; i < rechargeList.size(); i++) {
			String params = "orderId=" + rechargeList.get(i).getOrdersn();
			String res = HttpClientUtils.doPostForm(aliPayOrderQuery, params);
			if (res != null) {
				Map maps = JSON.parseObject(res);
				if (maps.get("code").equals("200")) {
					res = JSON.parseObject(maps.get("data").toString()).get("tradeStatus").toString();
				}
				if ("2".equals(res)) {
					Recharge recharge = new Recharge();
					recharge.setCustomerId(rechargeList.get(i).getCustomerId());
					recharge.setFinishDate(new Date());
					recharge.setOrdersn(rechargeList.get(i).getOrdersn());
					recharge.setState(2);// 状态。1-申请支付，2-支付成功 3支付失败
					// 入账
					accountMapper.inAccount(rechargeList.get(i).getCustomerId(), rechargeList.get(i).getAmount(),
							TransferTypeEnum.RECHARGE.getType());
					// 记录流水
					TradeDetail tradeDetail = new TradeDetail();
					tradeDetail.setTradeId(UUIDUtils.getId());
					tradeDetail.setCustomerId(rechargeList.get(i).getCustomerId());
					tradeDetail.setCreateTime(new Date());
					tradeDetail.setType(AccountBusinessTypeEnum.Recharge.getType());
					tradeDetail.setInPrice(rechargeList.get(i).getAmount());
					tradeDetailMapper.insertSelective(tradeDetail);
					rechargeMapper.updateByOrderNo(recharge);
					logger.info("修复成功一条异常充值消息----订单Id为" + rechargeList.get(i).getOrdersn());
				}
			}
		}

		logger.info("充值回调异常修复 job 结束------------------------");
	}
}