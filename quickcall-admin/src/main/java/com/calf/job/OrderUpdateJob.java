//package com.calf.job;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import com.calf.cn.service.BaseManager;
//import com.calf.module.order.entity.Order;
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
//    @Autowired
//    private BaseManager baseManager;
//
//    /**默认超时分钟数*/
//    private final static  Integer   DEFAULT_OVER_TIME_MINUTES = -30;
//
//    
//    
//    
//    @Scheduled(cron = "0 0/30 * * * ?")
////    @Scheduled(cron = "0 * * * * ?")
//    public void updateOrderStatus() {
//    	LOGGER.info("=============修改订单状态自动任务开始=================");
//    	try {
//    		//未支付超时订单处理
//    		Integer  orderStatus = 1;//未支付
//    		Date  currTime = new Date();
//    		//当前时间减去30分钟
//    		Calendar  cal =  Calendar.getInstance();
//    		cal.setTime(currTime);
//    		cal.add(Calendar.MINUTE, DEFAULT_OVER_TIME_MINUTES);
//    		Date  endTime =  cal.getTime();
//    		Map<String,Object> parameters = new HashMap<String,Object>();
//    		parameters.put("orderStatus", orderStatus);
//    		parameters.put("endTime", endTime);
//			List<Long>  orderIdList = baseManager.query("Order.queryPayOverTimeOrderIdList", parameters);
//    		
//    		if(!CollectionUtils.isEmpty(orderIdList)){
//    			Integer  orderStatusUpdate =  3;
//    			parameters = new HashMap<String,Object>();
//        		parameters.put("orderStatus", orderStatusUpdate);
//        		parameters.put("list", orderIdList);
//    			baseManager.update("Order.batchUpdateOrderStatus", parameters);
//    		}else{
//    			LOGGER.info("===========没有已支付过期的订单");
//    		}
//    		
//    		//TODO  需要添加事务保证修改状态与退款能全部成功
//    		orderStatus = 4;//已支付
//    		//大V未接单超时
//    		parameters = new HashMap<String,Object>();
//    		parameters.put("orderStatus", orderStatus);
//    		parameters.put("endTime", endTime);
//    		List<Order>  orderList = baseManager.query("Order.queryReceiveOverTimeOrderIdList", parameters);
//    		
//    		if(!CollectionUtils.isEmpty(orderList)){
//    			orderIdList = new ArrayList<Long>();
//    			for (Order order : orderList) {
//					orderIdList.add(order.getOrderId());
//				}
//    			Integer  orderStatusUpdate =  5;
//    			parameters = new HashMap<String,Object>();
//        		parameters.put("orderStatus", orderStatusUpdate);
//        		parameters.put("list", orderIdList);
//    			baseManager.update("Order.batchUpdateOrderStatus", parameters);
//    			//根据订单信息进行退款
//    			
//    			for (Order order : orderList) {
//    				Long  buyerId =  order.getBuyerId();
//    				BigDecimal  orderAmount =  order.getOrderAmounts();
//    				parameters = new HashMap<String,Object>();
//            		parameters.put("amount", orderAmount);
//            		parameters.put("userId", buyerId);
//            		parameters.put("type", 1);
//        			baseManager.update("Order.inAccount", parameters);
//    			}
//    			
//    		}else{
//    			LOGGER.info("===========没有已支付大V未接受过期的订单");
//    		}
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
