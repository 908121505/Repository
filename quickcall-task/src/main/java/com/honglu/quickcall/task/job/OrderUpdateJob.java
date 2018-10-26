package com.honglu.quickcall.task.job;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.task.dao.TaskOrderMapper;
import com.honglu.quickcall.task.entity.TaskOrder;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单状态更新
 * @Package: com.calf.job 
 * @author: chenliuguang   
 * @date: 2018年9月29日 下午2:35:31
 */
@Component
public class OrderUpdateJob {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderUpdateJob.class);


    @Autowired
    private TaskOrderMapper    taskOrderMapper;
    

    @Reference(version = "0.0.1", group = "accountCenter")
    private IAccountOrderService iAccountOrderService;

    /**默认超时小时数*/
    private final static  Integer   END_OVER_TIME_HOUR = -12;
    /**默认超时分钟数*/
    private final static  Integer   RECEIVE_OVER_TIME_MINUTES = -15;
    /**立即服务超时分钟数*/
    private final static  Integer   START_OVER_TIME_MINUTES = -5;
    
    /**叫醒类型服务结束和进行中时间*/
    private final static  Integer   ORDER_END_TIME_MINUTES = 5;

    
    //接单设置
    
    @Scheduled(cron = "0 * * * * ?")
    public void updateOrderStatus() {
    	LOGGER.info("=============修改订单状态自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.MINUTE, RECEIVE_OVER_TIME_MINUTES);
    		Date  endTime =  cal.getTime();
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_YES; 
    		//获取接单设置超时
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE;
			
    		
    		//大V未接单返回给账户金额  先退款，再更新状态
    		List<TaskOrder>  orderList = taskOrderMapper.queryReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		refundToCustomer(orderList);
    		
    		
    		taskOrderMapper.waittingReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		
			
			
			
			
			//大V未发起立即服务超时
    		cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
    		endTime =  cal.getTime();
    		queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
    		updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START;
			
    		//大V未发起立即服务超时给账户金额
    		orderList = taskOrderMapper.queryReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		refundToCustomer(orderList);
    		
    		taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			
			
			
			
			
			//用户未接立即服务超时
			cal = Calendar.getInstance();
			cal.setTime(currTime);
			cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
			endTime =  cal.getTime();
			queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE;
			updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT;
			
			
			//大V未发起立即服务超时给账户金额
			orderList = taskOrderMapper.queryStartOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			refundToCustomer(orderList);
			taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			
			
			
			
			//叫醒自动转换为进行中状态
			//用户未接立即服务超时
			queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START;
			updateStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
			skillType = OrderSkillConstants.SKILL_TYPE_NO;
			taskOrderMapper.appointOrderGoing(currTime,endTime, queryStatus, updateStatus, skillType);
			
			
			cal = Calendar.getInstance();
			
			cal.add(Calendar.MINUTE, ORDER_END_TIME_MINUTES);
			//叫醒自动转换为进行中状态
			//用户未接立即服务超时
			queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
			updateStatus = OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT;
			taskOrderMapper.appointOrderFinish(currTime,endTime, queryStatus, updateStatus, skillType);
			
    	} catch (Exception e) {
    		LOGGER.error("job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============修改订单状态自动任务结束=================");
    }
    
    
    @Scheduled(cron = "30 0/30 * * * ?")
    public void updateOrderStatusAfter12Hour() {
    	LOGGER.info("=============修改订单状态自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.HOUR_OF_DAY, END_OVER_TIME_HOUR);
    		Date  endTime =  cal.getTime();
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_YES; 
    		//获取接单设置超时
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_FINISH_DV_FINISH;
    		
    		
    		//服务完成，大V金额冻结
    		List<TaskOrder>  orderList = taskOrderMapper.queryOrderStatusAfter12HourCust(currTime, endTime, queryStatus, updateStatus, skillType);
    		freezeToService(orderList);
    		taskOrderMapper.updateOrderStatusAfter12HourCust(currTime, endTime, queryStatus, updateStatus, skillType);
    		
    		
    		
    		
    		cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.HOUR_OF_DAY, END_OVER_TIME_HOUR);
    		endTime =  cal.getTime();
    		skillType = OrderSkillConstants.SKILL_TYPE_NO; 
    		//获取接单设置超时
    		queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
    		updateStatus = OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE;
    		
    		
    		//服务完成，大V金额冻结
    		orderList = taskOrderMapper.queryOrderStatusAfter12HourBoth(currTime, endTime, queryStatus, updateStatus, skillType);
    		freezeToService(orderList);
    		taskOrderMapper.updateOrderStatusAfter12HourBoth(currTime, endTime, queryStatus, updateStatus, skillType);

    		
    		
    	} catch (Exception e) {
    		LOGGER.error("job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============修改订单状态自动任务结束=================");
    }
    
    
    
    
//    public  void  updateOrderStatusByOrderList(List<TaskOrder>  orderList,Integer  updateOrderStatus){
//    	
//    	if(!CollectionUtils.isEmpty(orderList)){
//    		List<Long>  orderIdList =  new ArrayList<Long>();
//    		for (TaskOrder order : orderList) {
//    			orderIdList.add(order.getOrderId());
//    		}
//    		taskOrderMapper.updateOrderStatus(updateOrderStatus, orderIdList);
//    	}
//    }
    
    
    /**
     * 订单取消，退款给用户
     * @param orderList
     */
    public   void   refundToCustomer(List<TaskOrder>  orderList){
    	if(!CollectionUtils.isEmpty(orderList)){
			for (TaskOrder order : orderList) {
				Long  customerId =  order.getCustomerId();
				BigDecimal  payAmount =  order.getOrderAmounts();
				//调用接口退款给用户
				iAccountOrderService.inAccount(customerId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
				LOGGER.info("用户ID："+customerId +"订单超时，系统自动退款给用户"+payAmount);
			}
		}
    }
    
    /**
     * 订单结束，大V金额冻结
     * @param orderList
     */
    public   void   freezeToService(List<TaskOrder>  orderList){
    	if(!CollectionUtils.isEmpty(orderList)){
    		for (TaskOrder order : orderList) {
    			Long  serviceId =  order.getServiceId();
    			BigDecimal   payAmount =  order.getOrderAmounts();
    			//大V冻结
    			iAccountOrderService.inAccount(order.getServiceId(), order.getOrderAmounts(), TransferTypeEnum.FROZEN, AccountBusinessTypeEnum.FroZen);
    			LOGGER.info("主播用户ID："+serviceId +"订单超时，系统自动退款给用户"+payAmount);
    		}
    	}
    }
    
    
    
    

}
