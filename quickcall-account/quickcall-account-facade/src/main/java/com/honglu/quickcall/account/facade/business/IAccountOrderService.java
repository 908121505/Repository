package com.honglu.quickcall.account.facade.business;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：账户中心--订单信息
 * @Package: com.honglu.quickcall.account.facade.business 
 * @author: chenliuguang   
 * @date: 2018年10月24日 下午1:45:23
 */
public interface IAccountOrderService {

    /**
     * 根据用户技能ID判断用户是否可以接单
     * @param customerId
     * @param custSkillId
     * @return
     */
    Integer checkReceiveOrderByCustomerSkillId(Long customerId,Long  custSkillId);
}
