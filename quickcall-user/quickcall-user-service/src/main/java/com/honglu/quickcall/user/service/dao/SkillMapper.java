package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.Skill;

public interface SkillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);
    
    List<Skill> selectAllSkill();
}