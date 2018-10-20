package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.CustomerSkillExt;

public interface CustomerSkillExtMapper {
    int deleteByPrimaryKey(Long custSkillExtId);

   
    int insert(CustomerSkillExt record);

   
    int insertSelective(CustomerSkillExt record);


    CustomerSkillExt selectByPrimaryKey(Long custSkillExtId);


    int updateByPrimaryKeySelective(CustomerSkillExt record);

   
    int updateByPrimaryKey(CustomerSkillExt record);
}