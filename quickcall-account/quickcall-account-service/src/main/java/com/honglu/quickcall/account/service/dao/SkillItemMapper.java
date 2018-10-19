package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.SkillItem;

public interface SkillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkillItem record);

    int insertSelective(SkillItem record);

    SkillItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkillItem record);

    int updateByPrimaryKey(SkillItem record);
}