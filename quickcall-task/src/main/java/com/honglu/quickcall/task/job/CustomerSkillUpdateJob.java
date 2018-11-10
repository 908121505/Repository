package com.honglu.quickcall.task.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.task.dao.TaskCustomerSkillMapper;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：接单设置定时任务
 * @Package: com.honglu.quickcall.task.job 
 * @author: chenliuguang   
 * @date: 2018年11月4日 下午4:59:57
 */
@Component
public class CustomerSkillUpdateJob {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerSkillUpdateJob.class);
    
    /**接单开关1：开启*/
    public static final Integer  RECEIVE_STATUS_OPEN = 1 ;
    /**接单开关0：关闭*/
    public static final Integer  RECEIVE_STATUS_CLOSE = 0 ;


    @Autowired
    private TaskCustomerSkillMapper    taskCustomerSkillMapper;


    
    
    
    /***根据周索引开启接单开关*/
    @Scheduled(cron = "0 * * * * ?")
    public void openCloseReceiveSwitch() {
    	openReceiveByWeek();
    	openReceiveByCurrTime();
    	closeReceiveByWeek();
    	closeReceiveByCurrTime();
    	
    	
    }
    /***根据周索引开启接单开关*/
//    @Scheduled(cron = "55 * * * * ?")
    public void openReceiveByWeek() {
    	LOGGER.info("=============week_close自动任务开始=================");
    	try {
    		
    		//判断当前时间是星期几
    		Integer   weekIndex = DateUtils.getDayOfWeek();
    		//自动开关开启
    		Integer  autoReceiveStatus  = OrderSkillConstants.AUTO_RECEIVE_OPEN;
    		//更新为开启
    		Integer  updateStatus = OrderSkillConstants.AUTO_RECEIVE_OPEN;
    		//接单开关关闭
    		Integer  receiveStatus =  OrderSkillConstants.RECEIVE_CLOSE;
    		//当前时间字符串
    		String  currTimeStr = DateUtils.formatDateHHMM(new Date());
    		taskCustomerSkillMapper.openReceiveByWeek(autoReceiveStatus, receiveStatus, updateStatus, weekIndex, currTimeStr);
    	} catch (Exception e) {
    		LOGGER.error("week_close自动任务关发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============week_close自动任务结束=================");
    }
    
    
    /*** 根据当前时间开启接单开关*/
//    @Scheduled(cron = "50 * * * * ?")
    public void openReceiveByCurrTime() {
    	LOGGER.info("============= curr_open自动任务开始=================");
    	try {
    		
    		//自动开关开启
    		Integer  autoReceiveStatus  = OrderSkillConstants.AUTO_RECEIVE_OPEN;
    		//更新为开启
    		Integer  updateStatus = OrderSkillConstants.AUTO_RECEIVE_OPEN;
    		//接单开关关闭
    		Integer  receiveStatus =  OrderSkillConstants.RECEIVE_CLOSE;
    		//当前时间
    		Date  currTime = new Date();
    		taskCustomerSkillMapper.openReceiveByCurrentTime(autoReceiveStatus, receiveStatus, updateStatus, currTime);
    	} catch (Exception e) {
    		LOGGER.error("curr_open自动任务发生异常，异常信息：", e);
    	}
    	LOGGER.info("============= curr_open自动任务结束=================");
    }
    /***根据周索引关闭接单开关*/
//    @Scheduled(cron = "45 * * * * ?")
    public void closeReceiveByWeek() {
    	LOGGER.info("=============week_close自动任务开始=================");
    	try {
    		
    		//判断当前时间是星期几
    		Integer   weekIndex = DateUtils.getDayOfWeek();
    		//自动开关开启
    		Integer  autoReceiveStatus  = OrderSkillConstants.AUTO_RECEIVE_OPEN;
    		//更新为关闭
    		Integer  updateStatus = OrderSkillConstants.AUTO_RECEIVE_CLOSE;
    		//接单开关开启
    		Integer  receiveStatus =  OrderSkillConstants.RECEIVE_OPEN;
    		//当前时间字符串
    		String  currTimeStr = DateUtils.formatDateHHMM(new Date());
    		taskCustomerSkillMapper.closeReceiveByWeek(autoReceiveStatus, receiveStatus, updateStatus, weekIndex, currTimeStr);
    	} catch (Exception e) {
    		LOGGER.error("week_close自动任务发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============week_close自动任务结束=================");
    }
    
    
    /***根据当前时间关闭接单开关*/
//    @Scheduled(cron = "40 * * * * ?")
    public void closeReceiveByCurrTime() {
    	LOGGER.info("============= curr_close自动任务开始=================");
    	try {
    		
    		//自动开关开启
    		Integer  autoReceiveStatus  = OrderSkillConstants.AUTO_RECEIVE_OPEN;
    		//更新为开启
    		Integer  updateStatus = OrderSkillConstants.AUTO_RECEIVE_CLOSE;
    		//接单开关--开启
    		Integer  receiveStatus =  OrderSkillConstants.RECEIVE_OPEN;
    		//当前时间
    		Date  currTime = new Date();
    		taskCustomerSkillMapper.closeReceiveByCurrentTime(autoReceiveStatus, receiveStatus, updateStatus, currTime);
    	} catch (Exception e) {
    		LOGGER.error("curr_close自动任务发生异常，异常信息：", e);
    	}
    	LOGGER.info("============= curr_close自动任务结束=================");
    }
    
    
    
    
    
    
    

    
    

}
