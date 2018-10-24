package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.enums.PayOrderStatusEnum;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.mq.pull.UserCenterMqExperienceListener;
import com.honglu.quickcall.user.service.service.CustomerGetExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

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
        // 查询客户下的订单
        Order order = customerMapper.selectCustomerOrder(request.getOrderId());
        if (order == null) {
            LOGGER.warn("客户下单消费获取经验值 -- 未查询到订单，订单ID：" + request.getOrderId());
            return;
        }

        // 服务完成
        if (!ifDoneOrder(order.getOrderStatus())) {
            LOGGER.warn("客户下单消费获取经验值 -- 订单未完成，订单状态：" + order.getOrderStatus());
            return;
        }

        // 查询下单人
        Customer customer = customerMapper.selectByPrimaryKey(order.getCustomerId());
        if (customer == null) {
            LOGGER.warn("客户下单消费获取经验值 -- 未查询到客户信息，客户ID：" + order.getCustomerId());
            return;
        }
        // 计算客户需要获取的经验值
        Integer experience = order.getOrderAmounts().multiply(new BigDecimal(100)).intValue();

        LOGGER.info("客户下单获取经验值--客户ID：" + customer.getCustomerId() + " ， 增加经验值：" + experience);
        // 更新用户经验值和等级
        customerMapper.updateCustomerExperienceAndLevel(customer.getCustomerId(), experience);
    }

    private boolean ifDoneOrder(Integer orderStatus) {
        return Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT) // 30.已完成（用户同意对方）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISH_DV_FINISH) // 32.已完成（大V发起已完成服务，12小时客户不响应自动完成）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_GOING_USRE_APPAY_FINISH) // 34.已完成（用户发起完成服务）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME) // 36.已完成（大V在服务时间外完成）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE) // 38.已完成（双方12小时内未发起完成服务）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISHED_AND_PINGJIA); // 40.已完成（用户评价完成）
    }
}
