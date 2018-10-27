package com.honglu.quickcall.user.facade.business;

/**
 * 用户中心发送MQ消息服务接口
 *
 * @author duanjun
 * @date 2018-10-24 10:10
 */
public interface UserCenterSendMqMessageService {

    /**
     * 发送MQ消息 -- 客户【下单花费】
     *
     * @param orderId
     */
    void sendOrderCostMqMessage(Long orderId);

    /**
     * 发送MQ消息 -- 客户【评价订单】
     *
     * @param orderId
     */
    void sendEvaluationOrderMqMessage(Long orderId);
}
