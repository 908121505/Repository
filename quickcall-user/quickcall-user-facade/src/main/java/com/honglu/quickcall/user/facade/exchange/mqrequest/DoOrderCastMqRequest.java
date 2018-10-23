package com.honglu.quickcall.user.facade.exchange.mqrequest;

import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

import java.io.Serializable;

/**
 * 下单消费MQ请求对象
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
public class DoOrderCastMqRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8298710509385584756L;

    /**
     * 订单ID
     */
    private Long orderId;

    @Override
    public String getBizCode() {
        return MqMessageServiceCode.CUSTOMER_EXPERIENCE_ORDER_COST;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
