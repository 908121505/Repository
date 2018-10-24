package com.honglu.quickcall.common.api.code;

/**
 * Mq消息服务业务类型编码
 *
 * @author duanjun
 * @date 2018-10-23 11:48
 */
public interface MqMessageServiceCode {
    /** 下单消费完成 -- 发送消息到MQ **/
    String CUSTOMER_EXPERIENCE_ORDER_COST = "100001";
    /** 客户评价订单 -- 发送消息到MQ **/
    String CUSTOMER_EVALUATION_ORDER = "100002";

    /**
     * 后台业务
     */
    /** 客户站内信发送 **/
    String ADMIN_CUSTOMER_MESSAGE = "200001";
}
