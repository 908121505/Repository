package com.honglu.quickcall.task.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.task.entity.CustomerCoupon;
import com.honglu.quickcall.task.vo.CouponOrderVo;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.entity.MessageCustomer;


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
	
	
    /**
     * 判断现在时间是否有活动
     * @return
     */
    int getActivityNum();
    
    /**
     * 根据客户ID和券ID查询用户券关系数量
     * @return Map<String,String> map  Long couponId,Long customerId
     */
    CustomerCoupon getCountByCustomerIdAndCouponId(Map<String,String> map);
    
    /**
     * 下单页数据展示优惠券接口用
     * @param map
     */
    CouponOrderVo showActivityCouponForOrder(Map<String,String> map);
    
    /**
     * 插入客户券关联信息
     * @param record
     * @return
     */
    int insertSelective(CustomerCoupon record);
    
    /**
     * 根据券ID查活动名字和券名字
     * @param couponId
     * @return
     */
    Map<String,String> selectActivityNameAndCouponName(@Param("couponId")Long couponId);
    
    /**
     * 根据客户ID查询电话
     * @param customerId
     * @mbggenerated
     */
    String selectPhoneByCustomerId(String customerId);
    
    /**
     * 插入信息Message
     * @param record
     * @mbggenerated
     */
    int insertSelectiveMessage(Message record);
    
    
    /**
     * 插入信息MessageCustomer
     * @param record
     * @mbggenerated
     */
    int insertSelectiveMessageCustomer(MessageCustomer record);
}