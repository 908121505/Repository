package com.honglu.quickcall.task.job;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honglu.quickcall.task.dao.DataBalanceMapper;

/**
 * 数据结算任务
 * @author zhaozheyi
 *
 */
@Component
public class DataBalanceJob {
	public static final Logger logger = LoggerFactory.getLogger(DataBalanceJob.class);
	@Autowired
	private DataBalanceMapper dataBalanceMapper;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void dataBanlance(){
		logger.info("执行数据运算Job任务----------------");
		//用户数据
		//用户注册量
		int registrationNum = dataBalanceMapper.queryRegistrationNum();
		//付费用户数
		int paidUsersNum = dataBalanceMapper.queryPaidUsersNum();
		//付费单品类人数
		int paidSkillOneNum = dataBalanceMapper.queryPaidSkillOneNum();
		//付费两个品类人数
		int paidSkillTwoNum = dataBalanceMapper.queryPaidSkillTwoNum();
		//付费三个品类人数
		int paidSkillThreeNum = dataBalanceMapper.queryPaidSkillThreeNum();
		Map<String,Integer> params = new HashMap<>();
		params.put("registrationNum", registrationNum);
		params.put("paidUsersNum", paidUsersNum);
		params.put("paidSkillOneNum", paidSkillOneNum);
		params.put("paidSkillTwoNum", paidSkillTwoNum);
		params.put("paidSkillThreeNum", paidSkillThreeNum);
		//把结算数据插入数据库
		dataBalanceMapper.insertCustomerData(params);
		logger.info("数据运算Job任务完成----------------");
	}
}
