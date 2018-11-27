package com.honglu.quickcall.user.service.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.SkillItem;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.SkillItemExtMapper;
import com.honglu.quickcall.user.service.dao.SkillItemMapper;
import com.honglu.quickcall.user.service.service.CustomerGetExperienceService;

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
    @Autowired
    private SkillItemExtMapper skillItemExtMapper;

    @Override
    public void doOrderCast(DoOrderCastMqRequest request) {
        // 查询客户下的订单
        Order order = customerMapper.selectCustomerOrder(request.getOrderId());
        if (order == null) {
            LOGGER.warn("客户下单消费获取经验值 -- 未查询到订单，订单ID：" + request.getOrderId());
            return;
        }

        // 查询下单人
        Customer customer = customerMapper.selectByPrimaryKey(order.getCustomerId());
        if (customer == null) {
            LOGGER.warn("客户下单消费获取经验值 -- 未查询到客户信息，客户ID：" + order.getCustomerId());
            return;
        }
      //TODO 根据地订单中customer_skill_id获取用户技能价格 * 订单数量orderNum
        BigDecimal skillPrice = skillItemExtMapper.selectOneSkillPrice(order.getSkillItemId());
        // 计算客户需要获取的经验值
        Integer experience = skillPrice.multiply(new BigDecimal(order.getOrderNum())).intValue();
//        Integer experience = order.getOrderAmounts().intValue();

        LOGGER.info("客户下单获取经验值--客户ID：" + customer.getCustomerId() + " ， 增加经验值：" + experience);
        // 更新用户经验值和等级
        customerMapper.updateCustomerExperienceAndLevel(customer.getCustomerId(), experience);
    }

}
