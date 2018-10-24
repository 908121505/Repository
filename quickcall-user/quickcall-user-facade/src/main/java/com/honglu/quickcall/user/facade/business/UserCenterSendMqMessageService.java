package com.honglu.quickcall.user.facade.business;

/**
 * 用户中心发送MQ消息服务接口
 *
 * @author duanjun
 * @date 2018-10-24 10:10
 */
public interface UserCenterSendMqMessageService {

    /**
     * 发送MQ消息 -- 客户【下单花费】 -- 获取经验值
     *
     * @param orderId
     */
    void sendOrderCostExperience(Long orderId);

    /**
     * 发送MQ消息 -- 客户【下单花费】 -- 更新主播排名
     *
     * @param orderId
     */
    void sendOrderCostScoreRank(Long orderId);

    /**
     * 发送MQ消息 -- 客户【评价订单】 -- 更新主播排名
     *
     * @param orderId
     */
    void sendEvaluationOrderScoreRank(Long orderId);
}
