package com.honglu.quickcall.user.facade.business;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;

/**
 * 用户
 *
 * @author duanjun
 * @date 2018-10-24 10:10
 */
public interface CustomerDubboBusiness {

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
