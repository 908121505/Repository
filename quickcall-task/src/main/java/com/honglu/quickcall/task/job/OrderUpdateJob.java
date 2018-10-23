//package com.honglu.quickcall.task.job;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
//import com.honglu.quickcall.task.dao.TaskOrderMapper;
//
///**
// * 
// * Copyright © 2018 www.xiaoniu.com All rights reserved.
// * 
// * 功能描述：订单状态更新
// * @Package: com.calf.job 
// * @author: chenliuguang   
// * @date: 2018年9月29日 下午2:35:31
// */
//@Component
//public class OrderUpdateJob {
//
//    public static final Logger LOGGER = LoggerFactory.getLogger(OrderUpdateJob.class);
//
//
//    @Autowired
//    private TaskOrderMapper    taskOrderMapper;
//
//    /**默认超时分钟数*/
//    private final static  Integer   RECEIVE_OVER_TIME_MINUTES = 15;
//    /**立即服务超时分钟数*/
//    private final static  Integer   START_OVER_TIME_MINUTES = 5;
//
//    
//    //接单设置
//    
//    @Scheduled(cron = "0 1 * * * ?")
//    public void updateOrderStatus() {
//    	LOGGER.info("=============修改订单状态自动任务开始=================");
//    	try {
//    		Date  currTime = new Date();
//    		
//    		Calendar  cal = Calendar.getInstance();
//    		
//    		cal.setTime(currTime);
//    		cal.add(Calendar.MINUTE, RECEIVE_OVER_TIME_MINUTES);
//    		Date  endTime =  cal.getTime();
//    		
//    		Integer skillType = OrderSkillConstants.SKILL_TYPE_NO; 
//    		//获取接单设置超时
//    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE;
//    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE;
//			taskOrderMapper.waittingReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
//    		
//			//大V未发起立即服务超时
//    		cal = Calendar.getInstance();
//    		cal.setTime(currTime);
//    		cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
//    		endTime =  cal.getTime();
//    		queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
//    		updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START;
//			taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
//			
//			
//			//用户未接立即服务超时
//			cal = Calendar.getInstance();
//			cal.setTime(currTime);
//			cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
//			endTime =  cal.getTime();
//			queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
//			updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START;
//			taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
//    		
//    		
//    		
//    		
//    		
//    		
//    		
//    	} catch (Exception e) {
//    		LOGGER.error("job执行发生异常，异常信息：", e);
//    	}
//    	LOGGER.info("=============修改订单状态自动任务结束=================");
//    }
//
//}
