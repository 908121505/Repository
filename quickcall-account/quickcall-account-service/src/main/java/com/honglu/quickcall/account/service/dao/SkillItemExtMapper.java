package com.honglu.quickcall.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.SkillItemExt;

public interface SkillItemExtMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SkillItemExt record);

    int insertSelective(SkillItemExt record);

    SkillItemExt selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SkillItemExt record);

    int updateByPrimaryKey(SkillItemExt record);

    /**根据技能分类ID 获取技能扩展信息列表,根据等级进行排序，且状态为可用，要限制类型*/
	List<SkillItemExt> querySkillItemExtList(@Param ("skillItemId")Long skillItemId,@Param ("skillExtType")Integer  skillExtType);
}