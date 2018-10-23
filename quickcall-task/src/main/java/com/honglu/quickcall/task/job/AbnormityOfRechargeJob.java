package com.honglu.quickcall.task.job;

import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honglu.quickcall.task.dao.RechargeMapper;
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

	private static String aliPayOrderQuery = ResourceBundle.getBundle("thirdconfig").getString("ALI_PAY_ORDER_QUERY");

	@Scheduled(cron = "* 0/15 * * * ?")
	public void execute() {

		logger.info("充值回调异常修复  job 开启------------------------");

		List<Recharge> rechargeList = rechargeMapper.queryAbnormityOfRecharge();
		for (int i = 0; i < rechargeList.size(); i++) {

		}

		logger.info("充值回调异常修复 job 结束------------------------");
	}
}
