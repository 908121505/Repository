package com.honglu.quickcall.user.facade.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiangping
 * @date 2018年10月28日 9点44分
 */
public class MessageCustomer implements Serializable {
    private static final long serialVersionUID = -7167561630995863410L;
    /**主键ID(15位时间+4位随机数)**/
    private Long id;
    /**用户手机号**/
    private Long phone;
    /**消息接受者**/
    private Long receiverId;
    /**消息ID**/
    private Long messageId;
    /**消息状态(0=未读,1=已读)**/
    private byte messageStatus;
    /**创建时间**/
    private Date createTime;
    /**修改时间**/
    private Date modifyTime;
    /**创建人**/
    private String createMan;
    /**修改人**/
    private String modifyMan;
    /**备注**/
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public byte getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(byte messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}