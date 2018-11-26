package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.user.service.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    private final static Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerSkillMapper customerSkillMapper;

    @Override
    public Customer queryCustomerByCustomerId(Long customerId) {
        return customerMapper.queryCustomerByCustomerId(customerId);
    }

    @Override
    public CustomerSkill queryCustomerSkillByCustomerId(Long customerId) {
        return customerSkillMapper.queryCustomerSkillByCustomerId(customerId);
    }
}
