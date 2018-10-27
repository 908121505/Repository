package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.facade.vo.DaVinfoVO;

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
     * 查询审核通过的技能
     *
     * @param skillItemId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<CustomerSkill> selectAuditedSkillByPage(@Param("skillItemId") Long skillItemId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查询用户审核通过的技能
     * @param customerId
     * @return
     */
    List<CustomerSkill> selectCustomerAuditedSkill(@Param("customerId") Long customerId);

    /**
     * 查询客户技能 热度评价标签
     * @param customerId
     * @param skillItemId
     * @return
     */
    List<String> selectCustomerSkillHotLabel(@Param("customerId") Long customerId, @Param("skillItemId") Long skillItemId);

    /**
     * 根据客户ID查客户信息
     * @param customerId
     * @return
     */
    CustomerSkill queryCustomerSkillByCustomerId(@Param("customerId") Long customerId);
    
	 /**
     * 分类显示大V列表
     * @param customerId
     * @param skillItemId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<DaVinfoVO> queryCustomerListBySkillItem(@Param("skillItemId") Long skillItemId,
    		@Param("start") Integer start, @Param("size") Integer size);
    
}