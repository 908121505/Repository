package com.honglu.quickcall.task.dao;

import com.honglu.quickcall.account.facade.entity.Order;

import java.util.List;

/**
 * 异步任务Mapper
 *
 * @author duanjun
 * @date 2018-09-24 15:31
 */
public interface TaskMapper {
    /**
     * 查询五分钟即将开始服务的订单
     *
     * @return
     */
    List<Order> queryWillBeStartMessage();

    /**
     * 根据客户ID获取用户的设备ID
     * @param customerId
     * @return
     */
    String getCustomerDeviceId(Long customerId);
}
