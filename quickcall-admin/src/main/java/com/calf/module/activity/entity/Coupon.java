package com.calf.module.activity.entity;

/**
 * Description: 优惠券
 *
 * @author chenpeng
 * @date 2018/10/31 10:44
 */
public class Coupon {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.coupon_id
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String couponId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.coupon_name
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String couponName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.is_permanent
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private Integer isPermanent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.coupon_type
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private Integer couponType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.start_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.end_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.acitivity_id
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String activityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.coupon_price
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private Integer couponPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.create_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.modify_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.create_man
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.modify_man
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.remark
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    private String remark;


    private String couponCode;
    private String activityName;
    private String activityCode;

    private Integer getWay;

    private String skillItemName;

    private String skillItemIdList;

    private String skillItemId;

    public String getSkillItemId() {
        return skillItemId;
    }

    public void setSkillItemId(String skillItemId) {
        this.skillItemId = skillItemId;
    }

    public String getSkillItemIdList() {
        return skillItemIdList;
    }

    public void setSkillItemIdList(String skillItemIdList) {
        this.skillItemIdList = skillItemIdList;
    }

    public String getSkillItemName() {
        return skillItemName;
    }

    public void setSkillItemName(String skillItemName) {
        this.skillItemName = skillItemName;
    }

    public Integer getGetWay() {
        return getWay;
    }

    public void setGetWay(Integer getWay) {
        this.getWay = getWay;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.coupon_id
     *
     * @return the value of coupon.coupon_id
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.coupon_id
     *
     * @param couponId the value for coupon.coupon_id
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.coupon_name
     *
     * @return the value of coupon.coupon_name
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.coupon_name
     *
     * @param couponName the value for coupon.coupon_name
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.is_permanent
     *
     * @return the value of coupon.is_permanent
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public Integer getIsPermanent() {
        return isPermanent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.is_permanent
     *
     * @param isPermanent the value for coupon.is_permanent
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setIsPermanent(Integer isPermanent) {
        this.isPermanent = isPermanent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.coupon_type
     *
     * @return the value of coupon.coupon_type
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.coupon_type
     *
     * @param couponType the value for coupon.coupon_type
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.start_time
     *
     * @return the value of coupon.start_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.start_time
     *
     * @param startTime the value for coupon.start_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.end_time
     *
     * @return the value of coupon.end_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.end_time
     *
     * @param endTime the value for coupon.end_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.acitivity_id
     *
     * @return the value of coupon.acitivity_id
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.acitivity_id
     *
     * @param activityId the value for coupon.acitivity_id
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.coupon_price
     *
     * @return the value of coupon.coupon_price
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public Integer getCouponPrice() {
        return couponPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.coupon_price
     *
     * @param couponPrice the value for coupon.coupon_price
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.create_time
     *
     * @return the value of coupon.create_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.create_time
     *
     * @param createTime the value for coupon.create_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.modify_time
     *
     * @return the value of coupon.modify_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.modify_time
     *
     * @param modifyTime the value for coupon.modify_time
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.create_man
     *
     * @return the value of coupon.create_man
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.create_man
     *
     * @param createMan the value for coupon.create_man
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.modify_man
     *
     * @return the value of coupon.modify_man
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.modify_man
     *
     * @param modifyMan the value for coupon.modify_man
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.remark
     *
     * @return the value of coupon.remark
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.remark
     *
     * @param remark the value for coupon.remark
     *
     * @mbggenerated Tue Oct 30 14:56:24 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
