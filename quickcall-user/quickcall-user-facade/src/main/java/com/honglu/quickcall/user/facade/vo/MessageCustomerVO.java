package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 22点44分
 */
public class MessageCustomerVO implements Serializable {
    private static final long serialVersionUID = -7167561630995863410L;

    /**
     * 标题
     */
    private String title;

    /**
     * H5链接
     */
    private String targetUrl;

    /**
     * 背景图
     */
    private String backgroundImageUrl;

    /**
     * 消息描述(备用字段)
     */
    private String messageDescribe;

    /**
     * 消息内容
     */
    private String messageContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getMessageDescribe() {
        return messageDescribe;
    }

    public void setMessageDescribe(String messageDescribe) {
        this.messageDescribe = messageDescribe;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "MessageCustomerVO{" +
                "title='" + title + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                ", messageDescribe='" + messageDescribe + '\'' +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}