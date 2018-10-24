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
     * 判断用户是否可以接单
     * @param customerSkillId
     * @return
     * 0:不可接单   1：可接单
     */
    Integer checkReceiveOrderByCustomerSkillId(Long customerSkillId);
}
