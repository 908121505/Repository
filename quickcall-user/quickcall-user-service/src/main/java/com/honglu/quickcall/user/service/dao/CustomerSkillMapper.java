package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerSkillMapper {
  
    int deleteByPrimaryKey(Long userSkillId);

    int insert(CustomerSkill record);

    int insertSelective(CustomerSkill record);

    CustomerSkill selectByPrimaryKey(Long userSkillId);

    int updateByPrimaryKeySelective(CustomerSkill record);

    int updateByPrimaryKey(CustomerSkill record);

    /**
     * 查询用户审核通过的技能
     * @param customerId
     * @return
     */
    List<CustomerSkill> selectCustomerAuditedSkill(@Param("customerId") Long customerId);
}