package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.CustomerMsgSetting;

public interface CustomerMsgSettingMapper {
    List<CustomerMsgSetting> selectByPrimaryKey(Long customerId);

    int insertSelective(CustomerMsgSetting record);
    int updateByPrimaryKeySelective(CustomerMsgSetting record);
    int updateByPrimaryKey(CustomerMsgSetting record);
}