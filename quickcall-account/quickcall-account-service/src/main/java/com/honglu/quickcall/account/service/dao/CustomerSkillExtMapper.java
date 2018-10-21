package com.honglu.quickcall.account.service.dao;

import java.util.List;

import com.honglu.quickcall.account.facade.entity.CustomerSkillExt;

public interface CustomerSkillExtMapper {
    int deleteByPrimaryKey(Long custSkillExtId);

   
    int insert(CustomerSkillExt record);

   
    int insertSelective(CustomerSkillExt record);


    CustomerSkillExt selectByPrimaryKey(Long custSkillExtId);


    int updateByPrimaryKeySelective(CustomerSkillExt record);

   
    int updateByPrimaryKey(CustomerSkillExt record);


    /**根据用户技能ID列表查询扩展性信息*/
	List<CustomerSkillExt> queryCustomerSkillExtList(List<Long> skillIdList);


	/**获取选中的数据*/
	List<CustomerSkillExt> querySelectInfo(Long customerSkilId);
}