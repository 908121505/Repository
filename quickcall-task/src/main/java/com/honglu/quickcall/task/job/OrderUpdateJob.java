package com.honglu.quickcall.task.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.task.dao.AccountMapper;
import com.honglu.quickcall.task.dao.BigvScoreMapper;
import com.honglu.quickcall.task.dao.BigvSkillScoreMapper;
import com.honglu.quickcall.task.dao.CustomerMapper;
import com.honglu.quickcall.task.dao.TaskOrderMapper;
import com.honglu.quickcall.task.dao.TradeDetailMapper;
import com.honglu.quickcall.task.entity.TaskOrder;
import com.honglu.quickcall.task.entity.TradeDetail;
import com.honglu.quickcall.user.facade.constants.ScoreRankConstants;
import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.entity.Customer;

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
    @Autowired
    private CustomerMapper customerMapper ;
    @Autowired
    private BigvSkillScoreMapper bigvSkillScoreMapper ;
    @Autowired
    private BigvScoreMapper bigvScoreMapper ;
    
    /**默认超时小时数      扣减12小时*/
    private final static  Integer   END_OVER_TIME_HOUR = -12;
    /**默认超时分钟数   扣减15分钟*/
    private final static  Integer   RECEIVE_OVER_TIME_MINUTES = -15;
    /**立即服务超时分钟数   扣减5分钟*/
    private final static  Integer   START_OVER_TIME_MINUTES = -5;
    
    
    
    
    private  final static Integer  CANCEL_ONE =  1 ;
    private  final static Integer  CANCEL_TWO =  2 ;
    private  final static Integer  CANCEL_THREE = 3 ;
    private  final static Integer  CANCEL_FOUR =  4 ;

    
    //接单设置
    
    /***大V15分钟未接单超时*/
    @Scheduled(cron = "5 * * * * ?")
    public void updateOrderStatusReceiveOrder() {
    	
    	
    	LOGGER.info("=============大V15分钟未接单超时自动任务开始=================");
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
			
    		Date  queryEndTime =  getEndTimeByAddHours(-1);
    		//大V未接单返回给账户金额  先退款，再更新状态
    		List<TaskOrder>  orderList = taskOrderMapper.queryReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType,queryEndTime);
    		refundToCustomer(orderList,CANCEL_ONE);
    		updateOrderStatusByOrderListForCancel(orderList, updateStatus);
//    		taskOrderMapper.waittingReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			
    	} catch (Exception e) {
    		LOGGER.error("大V15分钟未接单超时执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============大V15分钟未接单超时自动任务结束=================");
    }
    
    
    

    
    /**大V5分钟未发起立即服务超时*/
    @Scheduled(cron = "10 * * * * ?")
    public void updateOrderStatusDvStartService() {
    	LOGGER.info("=============大V5分钟未发起立即服务超时自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		//大V未发起立即服务超时
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
    		Date  endTime =  cal.getTime();
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START;
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_YES; 
    		//大V未发起立即服务超时给账户金额
    		Date  queryEndTime =  getEndTimeByAddHours(-1);
    		List<TaskOrder>  orderList = taskOrderMapper.queryStartOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType,queryEndTime);
    		refundToCustomer(orderList,CANCEL_TWO);
    		updateOrderStatusByOrderListForCancel(orderList, updateStatus);
//    		taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		
    	} catch (Exception e) {
    		LOGGER.error("大V5分钟未发起立即服务超时job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============大V5分钟未发起立即服务超时自动任务结束=================");
    }
    
    
    /**用户5分钟未响应大V发起立即服务超时*/
    @Scheduled(cron = "15 * * * * ?")
    public void updateOrderStatusCustNotConfirmStartService() {
    	LOGGER.info("=============用户5分钟未响应大V发起立即服务超时自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_YES; 
    		//用户未接立即服务超时
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
    		Date  endTime =  cal.getTime();
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT;
    		
    		
    		//用户5分钟未响应大V未发起立即服务超时给账户金额
    		Date  queryEndTime =  getEndTimeByAddHours(-1);
    		List<TaskOrder>  orderList = taskOrderMapper.queryStartOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType,queryEndTime);
    		refundToCustomer(orderList,CANCEL_THREE);
    		updateOrderStatusByOrderListForCancel(orderList, updateStatus);
//    		taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    	} catch (Exception e) {
    		LOGGER.error("用户5分钟未响应大V发起立即服务超时job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============用户5分钟未响应大V发起立即服务超时自动任务结束=================");
    }
    
    
    /**叫醒服务达到预约时间自动转为进行中*/
    @Scheduled(cron = "0 * * * * ?")
    public void updateOrderStatusAppointGoing() {
    	LOGGER.info("=============叫醒服务达到预约时间自动转为进行中自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		//叫醒自动转换为进行中状态
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		//预约时间去当前时间
    		Date  endTime =  cal.getTime();
    		//用户未接立即服务超时
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
    		Integer  skillType = OrderSkillConstants.SKILL_TYPE_NO;
    		Date  queryEndTime =  getEndTimeByAddDays(-2);
    		List<TaskOrder>  orderList = taskOrderMapper.queryAppointOrderGoing(currTime,endTime, queryStatus, updateStatus, skillType,queryEndTime);
    		updateOrderStatusByOrderListForCancel(orderList, updateStatus);
//    		taskOrderMapper.appointOrderGoing(currTime,endTime, queryStatus, updateStatus, skillType,queryEndTime);
    	} catch (Exception e) {
    		LOGGER.error("叫醒服务达到预约时间自动转为进行中job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============叫醒服务达到预约时间自动转为进行中自动任务结束=================");
    }
    
    
    
    
    
    
    
    
    
    /**
     * 扫描频率控制在一分钟一次
     */
    @Scheduled(cron = "20 * * * * ?")
    public void updateOrderStatusAfter12HourCust() {
    	LOGGER.info(">>>>>>>>>>>>>>>>>>客户单方面未响应大V结束服务12小时超时job开始<<<<<<<<<<<<<<<<<<<<<");
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
    		
    		Date  queryEndTime =  getEndTimeByAddDays(-2);
    		//服务完成，大V金额冻结
    		List<TaskOrder>  orderList = taskOrderMapper.queryOrderStatusAfter12HourCust(currTime, endTime, queryStatus, updateStatus, skillType,queryEndTime);
    		freezeToService(orderList);
    		updateOrderStatusByOrderListForFinish(orderList, updateStatus);
    		sendMsgByOrderList(orderList, CANCEL_FOUR);
    	} catch (Exception e) {
    		LOGGER.error("客户单方面未响应大V结束服务12小时超时job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info(">>>>>>>>>>>>>>>>>>客户单方面未响应大V结束服务12小时超时job结束<<<<<<<<<<<<<<<<<<<<<");
    }
    
    /**
     * 扫描频率控制在一分钟一次
     */
    @Scheduled(cron = "25 * * * * ?")
    public void updateOrderStatusAfter12HourBoth() {
    	LOGGER.info(">>>>>>>>>>>>>>>>>>客户大V双方未响应大V结束服务12小时超时job开始<<<<<<<<<<<<<<<<<<<<<");
    	try {
    		Date  currTime = new Date();
    		Calendar  cal = Calendar.getInstance();
    		cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.HOUR_OF_DAY, END_OVER_TIME_HOUR);
    		Date  endTime =  cal.getTime();
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_NO; 
    		//获取接单设置超时
    		Integer queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
    		Integer updateStatus = OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE;
    		Date  queryEndTime =  getEndTimeByAddDays(-2);
    		//服务完成，大V金额冻结
    		List<TaskOrder>  orderList = taskOrderMapper.queryOrderStatusAfter12HourBoth(currTime, endTime, queryStatus, updateStatus, skillType,queryEndTime);
    		freezeToService(orderList);
    		updateOrderStatusByOrderListForFinish(orderList, updateStatus);
    		sendMsgByOrderList(orderList, CANCEL_FOUR);
    		
    	} catch (Exception e) {
    		LOGGER.error("客户大V双方未响应大V结束服务12小时超时job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info(">>>>>>>>>>>>>>>>>>客户大V双方未响应大V结束服务12小时超时job结束<<<<<<<<<<<<<<<<<<<<<");
    }
    
    
    
    
    /**
     * 根据订单ID批量更新订单信息
     * @param orderList
     * @param updateOrderStatus
     */
    public  void  updateOrderStatusByOrderListForCancel(List<TaskOrder>  orderList,Integer  updateOrderStatus){
    	
    	if(!CollectionUtils.isEmpty(orderList)){
    		List<Long>  orderIdList =  new ArrayList<Long>();
    		for (TaskOrder order : orderList) {
    			orderIdList.add(order.getOrderId());
    		}
    		taskOrderMapper.updateOrderStatus(updateOrderStatus, orderIdList,new Date());
    	}
    }
    /**
     * 根据订单ID批量更新订单信息
     * @param orderList
     * @param updateOrderStatus
     */
    public  void  updateOrderStatusByOrderListForFinish(List<TaskOrder>  orderList,Integer  updateOrderStatus){
    	
    	if(!CollectionUtils.isEmpty(orderList)){
    		List<Long>  orderIdList =  new ArrayList<Long>();
    		for (TaskOrder order : orderList) {
    			orderIdList.add(order.getOrderId());
    		}
    		taskOrderMapper.updateOrderStatusForFinish(updateOrderStatus, orderIdList,new Date());
    	}
    }
    
    
    /**
     * 订单结束推送消息
     * @param orderList
     */
    public   void   sendMsgByOrderList(List<TaskOrder>  orderList,Integer  cancelType){
    	if(!CollectionUtils.isEmpty(orderList)){
			try {
				for (TaskOrder order : orderList) {
					Long  customerId =  order.getCustomerId();
					sendOrderMessage(customerId, cancelType, false);
					sendOrderMessage(order.getServiceId(), cancelType, true);
					doOrderCastRank(order.getOrderId());
					doOrderCastJingYan(order.getOrderId());
				}
			} catch (Exception e) {
				LOGGER.error("用户退款发生异常，异常信息",e);
			}
		}
    }
    /**
     * 订单取消，退款给用户
     * @param orderList
     */
    public   void   refundToCustomer(List<TaskOrder>  orderList,Integer  cancelType){
    	if(!CollectionUtils.isEmpty(orderList)){
    		try {
    			for (TaskOrder order : orderList) {
    				Long  customerId =  order.getCustomerId();
    				BigDecimal  payAmount =  order.getOrderAmounts();
    				//调用接口退款给用户
    				LOGGER.info("OrderInfo"+order.toString());
    				inAccount(customerId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund,order.getOrderId());
    				LOGGER.info("CID："+customerId +"订单超时，系统自动退款给用户"+payAmount);
    				
    				sendOrderMessage(customerId, cancelType, false);
    				sendOrderMessage(order.getServiceId(), cancelType, true);
    			}
    		} catch (Exception e) {
    			LOGGER.error("用户退款发生异常，异常信息",e);
    		}
    	}
    }
    
    
    
    /***
     * 消息推送
     * @param customerId
     * @param serviceId
     * @param cancelType 1:15分钟未接受订单   2:大V5分钟未发起立即服务   3：用户未接受大V立即服务   4：订单自动完成
     * @param msgContent
     */
    public synchronized  void   sendOrderMessage(Long  customerId,Integer  cancelType,boolean  dvFlag){
    	
    	String  content =  null ;
    	String  remarkName = null ;
    	if(dvFlag){
    		remarkName = OrderSkillConstants.MSG_CONTENT_DAV ;
    		if(cancelType == CANCEL_ONE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_DV_15MINUTE_TIMEOUT ;
    		}else if (cancelType == CANCEL_TWO){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_DV_5MINUTE_START_TIMEOUT ;
    		}else if (cancelType == CANCEL_THREE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_DV_5MINUTE_CONFIRM_TIMEOUT ;
    		}else if(cancelType == CANCEL_FOUR){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CUST_NOT_PING_JIA ;
    		}
    	}else{
    		remarkName = OrderSkillConstants.MSG_CONTENT_C ;
    		if(cancelType == CANCEL_ONE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_15MINUTE_TIMEOUT ;
    		}else if (cancelType == CANCEL_TWO){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_5MINUTE_START_TIMEOUT ;
    		}else if (cancelType == CANCEL_THREE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_5MINUTE_CONFIRM_TIMEOUT ;
    		}else if(cancelType == CANCEL_FOUR){
//    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_FINISH ;
    		}
    	}
    	
    	LOGGER.info("----------------给customerId"+customerId+"推送消息："+content);
    	if(StringUtils.isNotBlank(content)){
//    		content = OrderSkillConstants.IM_MSG_CONTENT_DEFAULT ;
    		LOGGER.info("给customerId"+customerId+"推送消息："+content);
    		//下单成功后推送IM消息
    		RongYunUtil.sendOrderMessage(customerId, content,remarkName);
    	}
    }
    
    
    /**
     * 订单结束，大V金额冻结
     * @param orderList
     */
    public   void   freezeToService(List<TaskOrder>  orderList){
    	if(!CollectionUtils.isEmpty(orderList)){
    		try {
				for (TaskOrder order : orderList) {
					Long  serviceId =  order.getServiceId();
					BigDecimal   payAmount =  order.getOrderAmounts();
					//大V冻结
					inAccount(order.getServiceId(), order.getOrderAmounts(), TransferTypeEnum.FROZEN, AccountBusinessTypeEnum.FroZen,order.getOrderId());
					LOGGER.info("SY_ID："+serviceId +"资金流水冻结，冻结金额"+payAmount);
				}
			} catch (Exception e) {
				LOGGER.error("大V账户冻结发生异常，异常信息",e);
			}
    	}
    }
    
    
    
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private TradeDetailMapper tradeDetailMapper;

	private final static String froZenTime = ResourceBundle.getBundle("thirdconfig").getString("froZenTime");
    
    public void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType,Long orderNo) {

    	LOGGER.info("=======inAccount========customerId:"+customerId+",amount:"+amount +",orderNo:"+orderNo);
		// 入账
		accountMapper.inAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setOrderNo(orderNo);
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setInPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

		if (transferType == TransferTypeEnum.FROZEN) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + customerId;
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_STREAM + tradeDetail.getTradeId();
			String frozenTimeKey = RedisKeyConstants.ACCOUNT_USERFROZEN_Time + tradeDetail.getTradeId();
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue)) {
				userFrozenValue = userFrozenValue + "," + tradeDetail.getTradeId();
				JedisUtil.set(userFrozenkey, userFrozenValue);
			} else {
				JedisUtil.set(userFrozenkey, tradeDetail.getTradeId() + "");

			}
			JedisUtil.set(steamFrozenKey, amount + "");
			// 缓存24小时
			JedisUtil.set(frozenTimeKey, "1", Integer.parseInt(froZenTime));
			// 流水对应的订单Id
			JedisUtil.set(RedisKeyConstants.ACCOUNT_USERFROZEN_ORDER_NO, orderNo + "");

		}

	}
    
    
	public void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType, Long orderNo) {
		LOGGER.info(">>>>>>>>>>>>>>>outAccount>>>>>>>>>>>>>>>customerId:"+customerId+",amount:"+amount +",orderNo:"+orderNo);
		// 入账
		accountMapper.outAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setOrderNo(orderNo);
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setOutPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

	}
	
	
	
    public Date   getEndTimeByAddDays(Integer   addDays){
    	Calendar  cal =  Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_YEAR, addDays);
    	return cal.getTime();
    }
    public Date   getEndTimeByAddHours(Integer   addHours){
    	Calendar  cal =  Calendar.getInstance();
    	cal.add(Calendar.HOUR_OF_DAY, addHours);
    	return cal.getTime();
    }
    
    
    

    
    /**
     * 加经验
     * @param orderId
     */
    private void doOrderCastJingYan(Long  orderId) {
        // 查询客户下的订单
        Order order = customerMapper.selectCustomerOrder(orderId);
        if (order == null) {
            LOGGER.warn("客户下单消费获取经验值 -- 未查询到订单，订单ID：" + orderId);
            return;
        }

        // 查询下单人
        Customer customer = customerMapper.selectByPrimaryKey(order.getCustomerId());
        if (customer == null) {
            LOGGER.warn("客户下单消费获取经验值 -- 未查询到客户信息，客户ID：" + order.getCustomerId());
            return;
        }
        // 计算客户需要获取的经验值
        Integer experience = order.getOrderAmounts().intValue();

        LOGGER.info("客户下单获取经验值--客户ID：" + customer.getCustomerId() + " ， 增加经验值：" + experience);
        // 更新用户经验值和等级
        customerMapper.updateCustomerExperienceAndLevel(customer.getCustomerId(), experience);
    }
    
    /**
     * 计算排名
     * @param orderId
     */
    private void doOrderCastRank(Long  orderId) {
        LOGGER.info("客户下单消费 -- 更新主播评分排名表：" + orderId);

        // 查询客户下的订单
        Order order = customerMapper.selectCustomerOrder(orderId);
        if (order == null) {
            LOGGER.warn("客户下单消费 -- 更新主播评分排名表 -- 未查询到订单，订单ID：" + orderId);
            return;
        }

        if (order.getValueScore() != null) {
            LOGGER.warn("客户下单消费 -- 更新主播评分排名表 -- 该订单已计算过评分：" + order.getOrderId());
            return;
        }

        // 计算该订单对应的技能的评分
        BigDecimal score = calculateOrderSkillScore(order, ScoreRankConstants.DEFAULT_EVALUATION_LEVEL);

        // 更新订单价值得分
        bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), score);

        // 更新评分到大V技能评分表和总评分排名表
        updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), score);

    }
    
    
   	/**
	 * 计算该笔订单的评价值
	 *
	 * @param order
	 * @param evaluateStars
	 * @return
	 * @计算公式：完成一笔价值评价=[（log(100,该技能累计订单数+6))*10*平台笔数权重+笔单价*平台笔单价权重]*(评价*评价权重*平台价值权重)
	 * @总排名：个人总价值=所有单个技能累计价值之和
	 */
	private BigDecimal calculateOrderSkillScore(Order order, Integer evaluateStars) {
		// 查询该用户该技能的订单笔数
		Integer orderTotal = bigvSkillScoreMapper.selectBigvSkillOrderTotal(order.getCustomerSkillId());
		orderTotal = orderTotal == null ? 0 : orderTotal;

		// 计算技能总比价得分
		BigDecimal orderTotalScore = new BigDecimal((2 / Math.log10(orderTotal + 6))
				* 10 * ScoreRankConstants.PLATFORM_ORDER_NUM_TOTAL_WEIGHT);

		// 计算技能笔单价得分
		BigDecimal servicePriceScore = order.getServicePrice().multiply(new BigDecimal(ScoreRankConstants.PLATFORM_SINGLE_ORDER_PRICE_WEIGHT));

		// 计算总得分
		BigDecimal valueScore = orderTotalScore.add(servicePriceScore).multiply(calculateEvaluateScore(evaluateStars, order.getOrderNum()));

		// 四舍五入取整
		return valueScore.setScale(0, BigDecimal.ROUND_HALF_UP);
	}

    /**
     * 更新评分到大V技能评分表和总评分排名表
     *
     * @param customerId
     * @param skillItemId
     * @param score
     */
    private void updateToBigvScore(Long customerId, Long skillItemId, Long customerSkillId, BigDecimal score) {
        // 存入技能排名表
        if (bigvSkillScoreMapper.updateBigvSkillScore(customerSkillId, score) == 0) {
            // 更新失败则插入
            BigvSkillScore bigvSkillScore = new BigvSkillScore();
            bigvSkillScore.setId(UUIDUtils.getId());
            bigvSkillScore.setCustomerId(customerId);
            bigvSkillScore.setSkillItemId(skillItemId);
            bigvSkillScore.setCustomerSkillId(customerSkillId);
            bigvSkillScore.setOrderTotal(1);
            bigvSkillScore.setScoreTotal(score);
            bigvSkillScoreMapper.insert(bigvSkillScore);
        }

        // 存入大V排名表
        if (bigvScoreMapper.updateBigvScore(customerId, score) == 0) {
            // 更新失败则插入
            BigvScore bigvScore = new BigvScore();
            bigvScore.setId(UUIDUtils.getId());
            bigvScore.setCustomerId(customerId);
            bigvScore.setOrderTotal(1);
            bigvScore.setScoreTotal(score);
            bigvScoreMapper.insert(bigvScore);
        }
    }
    
    
  
    
    


	/**
	 * 计算评价得分
	 * @desc (评价*评价权重*平台价值权重)
	 * @param evaluateStars
	 * @param orderNum
	 * @return
	 */
	private static BigDecimal calculateEvaluateScore(Integer evaluateStars, Integer orderNum) {
		if (evaluateStars == null) {
			evaluateStars = 0;
		}
		return new BigDecimal(ScoreRankConstants.EVALUATION_LEVEL_WEIGHT_MAP.get(evaluateStars)
				* ScoreRankConstants.getSingleOrderNumWeight(orderNum)
				* ScoreRankConstants.PLATFORM_ORDER_EVALUATION_WEIGHT);
	}


}
