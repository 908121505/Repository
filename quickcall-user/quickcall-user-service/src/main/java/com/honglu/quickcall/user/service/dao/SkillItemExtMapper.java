package com.honglu.quickcall.user.service.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.SkillItemExt;

public interface SkillItemExtMapper {
    int deleteByPrimaryKey(Integer id);

    
    int insert(SkillItemExt record);

  
    int insertSelective(SkillItemExt record);


    SkillItemExt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkillItemExt record);

    int updateByPrimaryKey(SkillItemExt record);
    
    /**
	 * 根据技能Id查询一项技能的价格
	 * 
	 * @param skillId
	 * @return
	 */
	BigDecimal selectOneSkillPrice(@Param("skillId") Long skillId);
}