package com.honglu.quickcall.user.facade.exchange.mqrequest;

import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.io.Serializable;

/**
 * 评价订单MQ请求对象
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
public class EvaluationOrderMqRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8567041487664833268L;
    /**
     * 订单ID
     */
    private Long orderId;

    @Override
    public String getBizCode() {
        return MqMessageServiceCode.CUSTOMER_EVALUATION_ORDER;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
