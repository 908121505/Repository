package com.honglu.quickcall.task.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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


    
    /***接单开关定时关闭*/
    @Scheduled(cron = "55 * * * * ?")
    public void updateOrderStatusReceiveOrder() {
    	LOGGER.info("=============接单开关定时关闭自动任务开始=================");
    	try {
    		Date  queryEndTime = new Date();
    		List<Long>  skillIdList = taskCustomerSkillMapper.queryCustomerSkill(RECEIVE_STATUS_OPEN, queryEndTime);
    		if(CollectionUtils.isEmpty(skillIdList)){
    			return  ;
    		}
    		taskCustomerSkillMapper.updateCustomerSkill(RECEIVE_STATUS_CLOSE, skillIdList);
    	} catch (Exception e) {
    		LOGGER.error("接单开关定时关闭发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============接单开关定时关闭自动任务结束=================");
    }
    
    
    

    
    

}
