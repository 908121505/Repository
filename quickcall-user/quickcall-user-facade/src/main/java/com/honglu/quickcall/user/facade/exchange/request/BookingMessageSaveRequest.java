package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 用户未读消息数量请求对象
 *
 * @author duanjun
 * @date 2018-09-22 17:11
 */
public class BookingMessageSaveRequest extends UserCenterRequest {
    private static final long serialVersionUID = -5124283956163159830L;
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
     * 单价：10音符/3小时
     */
    private String priceUnit;

    /**
     * 单价倍数
     */
    private String priceUnitTimeCount;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceUnitTimeCount() {
        return priceUnitTimeCount;
    }

    public void setPriceUnitTimeCount(String priceUnitTimeCount) {
        this.priceUnitTimeCount = priceUnitTimeCount;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.AddBookingMessage;
    }
}
