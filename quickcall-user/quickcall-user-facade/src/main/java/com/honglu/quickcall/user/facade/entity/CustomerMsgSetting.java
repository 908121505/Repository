package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

public class CustomerMsgSetting {
    /**
     * 主键ID(15位时间+4位随机数)
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Long id;

    /**
     * 客户ID
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Long customerId;

    /**
     * 接收类型(0=接收所有，1=仅接收粉丝)
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Integer receiveType;

    /**
     * 用户最小等级阈值,默认1000
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Integer rangeMinLimit;

    /**
     * 接收状态(0=开启，1=关闭)
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Integer receiveStatus;

    /**
     * 创建时间
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Date createTime;

    /**
     * 修改时间
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Date modifyTime;

    /**
     * 创建人
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private String createMan;

    /**
     * 修改人
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private String modifyMan;

    /**
     * 备注
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private String remark;

    /**
     *
     * @return the value of customer_msg_setting.id
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for customer_msg_setting.id
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of customer_msg_setting.customer_id
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId the value for customer_msg_setting.customer_id
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return the value of customer_msg_setting.receive_type
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Integer getReceiveType() {
        return receiveType;
    }

    /**
     *
     * @param receiveType the value for customer_msg_setting.receive_type
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    /**
     *
     * @return the value of customer_msg_setting.range_min_limit
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Integer getRangeMinLimit() {
        return rangeMinLimit;
    }

    /**
     *
     * @param rangeMinLimit the value for customer_msg_setting.range_min_limit
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setRangeMinLimit(Integer rangeMinLimit) {
        this.rangeMinLimit = rangeMinLimit;
    }

    /**
     *
     * @return the value of customer_msg_setting.receive_status
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    /**
     *
     * @param receiveStatus the value for customer_msg_setting.receive_status
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    /**
     *
     * @return the value of customer_msg_setting.create_time
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     * @param createTime the value for customer_msg_setting.create_time
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     * @return the value of customer_msg_setting.modify_time
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     *
     * @param modifyTime the value for customer_msg_setting.modify_time
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     *
     * @return the value of customer_msg_setting.create_man
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     *
     * @param createMan the value for customer_msg_setting.create_man
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     *
     * @return the value of customer_msg_setting.modify_man
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     *
     * @param modifyMan the value for customer_msg_setting.modify_man
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     *
     * @return the value of customer_msg_setting.remark
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     *
     * @param remark the value for customer_msg_setting.remark
     *
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}