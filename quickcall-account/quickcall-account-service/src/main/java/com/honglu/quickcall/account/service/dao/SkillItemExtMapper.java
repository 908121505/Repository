package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.SkillItemExt;

public interface SkillItemExtMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SkillItemExt record);

    int insertSelective(SkillItemExt record);

    SkillItemExt selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SkillItemExt record);

    int updateByPrimaryKey(SkillItemExt record);
}