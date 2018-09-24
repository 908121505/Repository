package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.enums.PushAppMsgTypeEnum;

/**
 * 推送APP消息Job任务请求对象
 *
 * @author duanjun
 * @date 2018-09-23 16:48
 */
public class PushAppMsgJobRequest extends AbstractRequest {

    private static final long serialVersionUID = -7150104096121843238L;
    /**
     * 消息类型
     */
    private PushAppMsgTypeEnum msgType;

    public PushAppMsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(PushAppMsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.PUSH_APP_MSG_JOB;
    }
}
