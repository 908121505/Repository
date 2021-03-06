package com.honglu.quickcall.activity.service.dao;

import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.facade.vo.CouponOrderVo;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.entity.MessageCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerCouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_coupon
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_coupon
     *
     * @mbggenerated
     */
    int insert(CustomerCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_coupon
     *
     * @mbggenerated
     */
    int insertSelective(CustomerCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_coupon
     *
     * @mbggenerated
     */
    CustomerCoupon selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_coupon
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CustomerCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_coupon
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CustomerCoupon record);

    int selectByCustomerIdAndCouponId(Map<String,String> map);

    /**
     * 根据订单ID查询客户优惠券
     * @return
     */
    Map<String,Object> getCustomerCouponByOrderId(@Param("orderId")Long orderId);

    /**
     * 查询券
     * @return
     */
    //String getCouponIdBySkillItemId(@Param("list") List<String> statusList);

    /**
     * 查询是否展示提示
     * @return
     */
    //int getShowTip(@Param("couponId")String couponId ,@Param("customerId")String customerId);


    CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(@Param("customerId") Long customerId, @Param("orderId") Long orderId);

    int cancelUpdateCustomerCoupon(@Param("id") Integer id);

    /**
     * 根据券ID查活动名字和券名字
     * @param couponId
     * @return
     */
    Map<String,String> selectActivityNameAndCouponName(@Param("couponId")Long couponId);

    /**
     * 判断现在时间是否有活动
     * @return
     */
    int getActivityNum();

    /**
     * 根据技能ID查询券ID
     * @return
     */
    String getCouponIdBySkillItemId(String skillItemId);

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
     * 获取可抵扣的优惠券
     * @param map
     */
    CouponOrderVo getDeductCoupon(Map<String,String> map);

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

    /**
     * 根据客户ID查询电话
     * @param customerId
     * @mbggenerated
     */
    String selectPhoneByCustomerId(String customerId);

}