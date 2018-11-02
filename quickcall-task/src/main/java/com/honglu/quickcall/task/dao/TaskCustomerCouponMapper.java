package com.honglu.quickcall.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：定时任务使用mapper
 * @Package: com.honglu.quickcall.task.dao 
 * @author: chenliuguang   
 * @date: 2018年11月2日 上午11:08:45
 */
@Mapper
public interface TaskCustomerCouponMapper {
   

	/**
	 * 根据订单ID列表更新用户券状态
	 * @param orderIdList
	 * @param couponFlag
	 */
	void  batchUpdateCustomerCoupon(@Param("list")List<Long>  orderIdList,@Param("couponFlag")Integer  couponFlag);
}