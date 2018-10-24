package com.honglu.quickcall.user.facade.business;

/**
 * 用户中心发送MQ消息服务接口
 *
 * @author duanjun
 * @date 2018-10-24 10:10
 */
public interface UserCenterSendMqMessageService {

    /**
     * 发送MQ消息 -- 客户【下单话费】获取经验值
     *
     * @param orderId
     */
    void sendOrderCostExperience(Long orderId);
}
