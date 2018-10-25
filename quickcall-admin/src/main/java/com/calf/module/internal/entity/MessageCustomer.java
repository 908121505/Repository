package com.calf.module.internal.entity;

import java.util.Date;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 22点14分
 * @description: 站内信消息查询
 */
public class MessageCustomer {
    /**
     * 主键ID(15位时间+4位随机数)
     */
    private String id;

    /**
     * 用户手机号
     */
    private Long phone;

    /**
     * 消息接受者
     */
    private Long receiverId;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 消息状态(0=未读,1=已读)
     */
    private Byte messageStatus;

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
    public String getId() {
        return id;
    }

    /**
     * 主键ID(15位时间+4位随机数)
     * @param id 主键ID(15位时间+4位随机数)
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 用户手机号
     * @return phone 用户手机号
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * 用户手机号
     * @param phone 用户手机号
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * 消息接受者
     * @return receiver_id 消息接受者
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 消息接受者
     * @param receiverId 消息接受者
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 消息ID
     * @return message_id 消息ID
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * 消息ID
     * @param messageId 消息ID
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 消息状态(0=未读,1=已读)
     * @return message_status 消息状态(0=未读,1=已读)
     */
    public Byte getMessageStatus() {
        return messageStatus;
    }

    /**
     * 消息状态(0=未读,1=已读)
     * @param messageStatus 消息状态(0=未读,1=已读)
     */
    public void setMessageStatus(Byte messageStatus) {
        this.messageStatus = messageStatus;
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