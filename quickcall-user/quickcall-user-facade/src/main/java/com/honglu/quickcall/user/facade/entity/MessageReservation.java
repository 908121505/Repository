package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

public class MessageReservation {
    /**
     * 主键ID(15位时间+4位随机数)
     */
    private Long id;

    /**
     * 客户ID（普通用户ID）
     */
    private Long customerId;

    /**
     * 消息接受者（大V用户ID）
     */
    private Long receiverId;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 单价：10音符/3小时
     */
    private String priceUnit;

    /**
     * 0.未读，1.已读
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String createMan;

    /**
     * 修改人
     */
    private String modifyMan;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主键ID(15位时间+4位随机数)
     * @return id 主键ID(15位时间+4位随机数)
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID(15位时间+4位随机数)
     * @param id 主键ID(15位时间+4位随机数)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户ID（普通用户ID）
     * @return customer_id 客户ID（普通用户ID）
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 客户ID（普通用户ID）
     * @param customerId 客户ID（普通用户ID）
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 消息接受者（大V用户ID）
     * @return receiver_id 消息接受者（大V用户ID）
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 消息接受者（大V用户ID）
     * @param receiverId 消息接受者（大V用户ID）
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 消息标题
     * @return title 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 消息标题
     * @param title 消息标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 消息内容
     * @return content 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 消息内容
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 单价：10音符/3小时
     * @return price_unit 单价：10音符/3小时
     */
    public String getPriceUnit() {
        return priceUnit;
    }

    /**
     * 单价：10音符/3小时
     * @param priceUnit 单价：10音符/3小时
     */
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    /**
     * 0.未读，1.已读
     * @return status 0.未读，1.已读
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 0.未读，1.已读
     * @param status 0.未读，1.已读
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 创建人
     * @return create_man 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     * @param createMan 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 修改人
     * @return modify_man 修改人
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * 修改人
     * @param modifyMan 修改人
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}