package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;

/**
 * 客户获取经验值接口
 *
 * @author duanjun
 * @date 2018-10-23 14:42
 */
public interface CustomerService {

    /**
     * 获取用户根据ID
     *
     * @param customerId
     */
    Customer queryCustomerByCustomerId(Long customerId);

    /**
     * 获取用户根据ID
     *
     * @param customerId
     */
    CustomerSkill queryCustomerSkillByCustomerId(Long customerId);
}
