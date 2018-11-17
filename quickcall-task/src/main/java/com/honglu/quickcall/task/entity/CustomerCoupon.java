package com.honglu.quickcall.task.entity;

import java.util.Date;

/**
 * 用户券关系
 */
public class CustomerCoupon {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.customer_id
     *
     * @mbggenerated
     */
    private Long customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.coupon_id
     *
     * @mbggenerated
     */
    private Long couponId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.is_used
     *
     * @mbggenerated
     */
    private Integer isUsed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.order_id
     *
     * @mbggenerated
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.create_man
     *
     * @mbggenerated
     */
    private String createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.modify_man
     *
     * @mbggenerated
     */
    private String modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_coupon.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.id
     *
     * @return the value of customer_coupon.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.id
     *
     * @param id the value for customer_coupon.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.customer_id
     *
     * @return the value of customer_coupon.customer_id
     *
     * @mbggenerated
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.customer_id
     *
     * @param customerId the value for customer_coupon.customer_id
     *
     * @mbggenerated
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.coupon_id
     *
     * @return the value of customer_coupon.coupon_id
     *
     * @mbggenerated
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.coupon_id
     *
     * @param couponId the value for customer_coupon.coupon_id
     *
     * @mbggenerated
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.is_used
     *
     * @return the value of customer_coupon.is_used
     *
     * @mbggenerated
     */
    public Integer getIsUsed() {
        return isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.is_used
     *
     * @param isUsed the value for customer_coupon.is_used
     *
     * @mbggenerated
     */
    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.order_id
     *
     * @return the value of customer_coupon.order_id
     *
     * @mbggenerated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.order_id
     *
     * @param orderId the value for customer_coupon.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.create_time
     *
     * @return the value of customer_coupon.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.create_time
     *
     * @param createTime the value for customer_coupon.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.modify_time
     *
     * @return the value of customer_coupon.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.modify_time
     *
     * @param modifyTime the value for customer_coupon.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.create_man
     *
     * @return the value of customer_coupon.create_man
     *
     * @mbggenerated
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.create_man
     *
     * @param createMan the value for customer_coupon.create_man
     *
     * @mbggenerated
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.modify_man
     *
     * @return the value of customer_coupon.modify_man
     *
     * @mbggenerated
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.modify_man
     *
     * @param modifyMan the value for customer_coupon.modify_man
     *
     * @mbggenerated
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_coupon.remark
     *
     * @return the value of customer_coupon.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_coupon.remark
     *
     * @param remark the value for customer_coupon.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}