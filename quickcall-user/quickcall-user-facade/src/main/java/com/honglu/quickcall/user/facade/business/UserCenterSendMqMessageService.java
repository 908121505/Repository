package com.honglu.quickcall.user.facade.business;

/**
 * 用户中心发送MQ消息服务接口
 *
 * @author duanjun
 * @date 2018-10-24 10:10
 */
public interface UserCenterSendMqMessageService {

    /**
     * 发送MQ消息 -- 客户【下单花费】 -- 服务完成时调用，但是每一笔订单仅调用一次
     *
     * @param orderId
     */
    void sendOrderCostMqMessage(Long orderId);

    /**
     * 发送MQ消息 -- 客户【评价订单】 -- 提交评价后调用，但是眉笔订单仅调用一次
     *
     * @param orderId
     */
    void sendEvaluationOrderMqMessage(Long orderId);
}
