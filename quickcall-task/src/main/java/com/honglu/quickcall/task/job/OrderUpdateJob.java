//package com.honglu.quickcall.task.job;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
//import com.honglu.quickcall.task.dao.TaskOrderMapper;
//import com.honglu.quickcall.task.entity.TaskOrder;
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
//    		//获取接单设置超时
//    		List<Integer>  statusList =  new ArrayList<Integer>();
//    		Integer  orderStatus = OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE;
//    		
//    		List<TaskOrder>  result = taskOrderMapper.getOverTimeOrderList(currTime, statusList);
//    		
//    		
//    		
//    		
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
