package com.calf.module.internal.entity;

import java.util.Date;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 22点14分
 * @description: 站内信消息查询
 */
public class Message {
    /**
     * 主键ID(15位时间+4位随机数)
     */
    private String messageId;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 标题
     */
    private String title;

    /**
     * H5链接
     */
    private String h5Url;

    /**
     * 发送状态(0=未发送,1=预约发送,2=已发送)
     */
    private Byte sendStatus;

    /**
     * 发送对象(0=指定用户,1=所有用户)
     */
    private Byte sendType;

    /**
     * 预约发送时间
     */
    private Date bespeakSendTime;

    /**
     * 背景图
     */
    private String backgroundImageUrl;

    /**
     * 消息描述
     */
    private String messageDescribe;

    /**
     * 消息类型(0=系统通知,1=活动通知,2=通知消息)
     */
    private Byte type;

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
     * @return message_id 主键ID(15位时间+4位随机数)
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 主键ID(15位时间+4位随机数)
     * @param messageId 主键ID(15位时间+4位随机数)
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 消息内容
     * @return message_content 消息内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 消息内容
     * @param messageContent 消息内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * H5链接
     * @return H5_url H5链接
     */
    public String getH5Url() {
        return h5Url;
    }

    /**
     * H5链接
     * @param h5Url H5链接
     */
    public void setH5Url(String h5Url) {
        this.h5Url = h5Url == null ? null : h5Url.trim();
    }

    /**
     * 发送状态(0=未发送,1=预约发送,2=已发送)
     * @return send_status 发送状态(0=未发送,1=预约发送,2=已发送)
     */
    public Byte getSendStatus() {
        return sendStatus;
    }

    /**
     * 发送状态(0=未发送,1=预约发送,2=已发送)
     * @param sendStatus 发送状态(0=未发送,1=预约发送,2=已发送)
     */
    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 发送对象(0=指定用户,1=所有用户)
     * @return send_type 发送对象(0=指定用户,1=所有用户)
     */
    public Byte getSendType() {
        return sendType;
    }

    /**
     * 发送对象(0=指定用户,1=所有用户)
     * @param sendType 发送对象(0=指定用户,1=所有用户)
     */
    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    /**
     * 预约发送时间
     * @return bespeak_send_time 预约发送时间
     */
    public Date getBespeakSendTime() {
        return bespeakSendTime;
    }

    /**
     * 预约发送时间
     * @param bespeakSendTime 预约发送时间
     */
    public void setBespeakSendTime(Date bespeakSendTime) {
        this.bespeakSendTime = bespeakSendTime;
    }

    /**
     * 背景图
     * @return background_image_url 背景图
     */
    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    /**
     * 背景图
     * @param backgroundImageUrl 背景图
     */
    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl == null ? null : backgroundImageUrl.trim();
    }

    /**
     * 消息描述
     * @return message_describe 消息描述
     */
    public String getMessageDescribe() {
        return messageDescribe;
    }

    /**
     * 消息描述
     * @param messageDescribe 消息描述
     */
    public void setMessageDescribe(String messageDescribe) {
        this.messageDescribe = messageDescribe == null ? null : messageDescribe.trim();
    }

    /**
     * 消息类型(0=系统通知,1=活动通知,2=通知消息)
     * @return type 消息类型(0=系统通知,1=活动通知,2=通知消息)
     */
    public Byte getType() {
        return type;
    }

    /**
     * 消息类型(0=系统通知,1=活动通知,2=通知消息)
     * @param type 消息类型(0=系统通知,1=活动通知,2=通知消息)
     */
    public void setType(Byte type) {
        this.type = type;
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