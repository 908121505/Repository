package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.enums.PushAppMsgTypeEnum;

/**
 * 推送APP消息请求对象
 *
 * @author duanjun
 * @date 2018-09-23 16:48
 */
public class PushAppMsgRequest extends AbstractRequest {

    /**
     * 消息类型
     */
    private PushAppMsgTypeEnum msgType;
    /**
     * 消息发送者
     */
    private Long senderId;
    /**
     * 消息接收者
     */
    private Long receiverId;

    public PushAppMsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(PushAppMsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.PUSH_APP_MSG;
    }
}
