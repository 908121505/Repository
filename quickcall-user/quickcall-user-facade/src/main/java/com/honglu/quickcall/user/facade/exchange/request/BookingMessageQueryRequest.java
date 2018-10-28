package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 用户未读消息数量请求对象
 *
 * @author duanjun
 * @date 2018-09-22 17:11
 */
public class BookingMessageQueryRequest extends UserCenterRequest {
    private static final long serialVersionUID = -5124283956163159830L;
    /**
     * 消息接受者（大V用户ID）
     */
    private Long receiverId;

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.QueryBookingMessage;
    }
}
