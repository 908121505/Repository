package com.calf.module.mq.request;

import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.io.Serializable;

/**
 * 发送站内信-MQ请求对象
 *
 * @author xiangxianjin
 * @date 2018-10-24 18:09
 */
public class CustomerMessageRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8298710509385584756L;

    /**
     * 消息编号
     */
    private String messageId;

    @Override
    public String getBizCode() {
        return MqMessageServiceCode.ADMIN_CUSTOMER_MESSAGE;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
