package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.enums.PayOrderStatusEnum;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.mq.pull.UserCenterMqExperienceListener;
import com.honglu.quickcall.user.service.service.CustomerGetExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户获取经验值接口实现类
 *
 * @author duanjun
 * @date 2018-10-23 14:42
 */
@Service
public class CustomerGetExperienceServiceImpl implements CustomerGetExperienceService {
    private final static Logger LOGGER = Logger.getLogger(CustomerGetExperienceServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void doOrderCast(DoOrderCastMqRequest request) {
//        // 查询客户下的订单
//        Order order = customerMapper.selectCustomerOrder(request.getOrderId());
//        if (order == null) {
//            LOGGER.warn("客户下单消费获取经验值 -- 未查询到订单-----------");
//            return;
//        }
//
//        // 服务完成
//        if(order.getOrderStatus() >= 30){
//
//        }
    }
}
