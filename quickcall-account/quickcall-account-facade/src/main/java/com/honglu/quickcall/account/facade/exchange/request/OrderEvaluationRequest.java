package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 订单评价页面 获取显示数据 - 请求对象
 *
 * @author duanjun
 * @date 2018-10-22 13:49
 */
public class OrderEvaluationRequest extends AbstractRequest {

    private static final long serialVersionUID = 3860913411326790170L;
    /**
     * 订单ID
     */
    private Long orderId;

    @Override
    public String getBizCode() {
        return OrderRequestType.ORDER_EVALUATION;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
