package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

public class CustomerDeviceWhitelist {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.customer_id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private Long customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.device_id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private String deviceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.device_type
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private String deviceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.status
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.create_time
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.modify_time
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.create_man
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private String createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.modify_man
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private String modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_device_whitelist.remark
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.id
     *
     * @return the value of customer_device_whitelist.id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.id
     *
     * @param id the value for customer_device_whitelist.id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.customer_id
     *
     * @return the value of customer_device_whitelist.customer_id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.customer_id
     *
     * @param customerId the value for customer_device_whitelist.customer_id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.device_id
     *
     * @return the value of customer_device_whitelist.device_id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.device_id
     *
     * @param deviceId the value for customer_device_whitelist.device_id
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.device_type
     *
     * @return the value of customer_device_whitelist.device_type
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.device_type
     *
     * @param deviceType the value for customer_device_whitelist.device_type
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.status
     *
     * @return the value of customer_device_whitelist.status
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.status
     *
     * @param status the value for customer_device_whitelist.status
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.create_time
     *
     * @return the value of customer_device_whitelist.create_time
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.create_time
     *
     * @param createTime the value for customer_device_whitelist.create_time
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.modify_time
     *
     * @return the value of customer_device_whitelist.modify_time
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.modify_time
     *
     * @param modifyTime the value for customer_device_whitelist.modify_time
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.create_man
     *
     * @return the value of customer_device_whitelist.create_man
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.create_man
     *
     * @param createMan the value for customer_device_whitelist.create_man
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.modify_man
     *
     * @return the value of customer_device_whitelist.modify_man
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.modify_man
     *
     * @param modifyMan the value for customer_device_whitelist.modify_man
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_device_whitelist.remark
     *
     * @return the value of customer_device_whitelist.remark
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_device_whitelist.remark
     *
     * @param remark the value for customer_device_whitelist.remark
     *
     * @mbggenerated Fri Nov 02 15:41:38 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}