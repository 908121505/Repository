package com.honglu.quickcall.task.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.honglu.quickcall.account.facade.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * 异步任务Mapper
 *
 * @author duanjun
 * @date 2018-09-24 15:31
 */
public interface TaskMapper {
    /**
     * 查询五分钟即将开始服务的订单
     *
     * @return
     */
    List<Order> queryWillBeStartMessage();

    /**
     * 根据客户ID获取用户的设备ID
     * @param customerId
     * @return
     */
    String getCustomerDeviceId(Long customerId);

	/**
	 * 入账
	 * 
	 * @param customerId
	 * @param inMoney
	 * @param type
	 *            1 +可用金额 2 +提现金额出账
	 */
	void inAccount(@Param("userId") Long customerId, @Param("amount") BigDecimal inMoney, @Param("type") Integer type);

	/**
	 * 出账
	 * 
	 * @param customerId
	 * @param outMoney
	 * @param type
	 *            1 -可用金额 2 -提现金额
	 */
	void outAccount(@Param("userId") Long customerId, @Param("amount") BigDecimal outMoney,
			@Param("type") Integer type);
    
}
