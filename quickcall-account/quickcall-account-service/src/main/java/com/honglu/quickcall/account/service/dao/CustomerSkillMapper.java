package com.honglu.quickcall.account.service.dao;

import java.util.List;

import com.honglu.quickcall.account.facade.entity.CustomerSkill;

public interface CustomerSkillMapper {
    int deleteByPrimaryKey(Long userSkillId);

    int insert(CustomerSkill record);

    
    int insertSelective(CustomerSkill record);

    CustomerSkill selectByPrimaryKey(Long userSkillId);

    int updateByPrimaryKeySelective(CustomerSkill record);

    int updateByPrimaryKey(CustomerSkill record);

    
    /**根据客户编号查询客户所有的技能信息*/
	List<CustomerSkill> querySkillInfoPersonal(Long customerId);
}