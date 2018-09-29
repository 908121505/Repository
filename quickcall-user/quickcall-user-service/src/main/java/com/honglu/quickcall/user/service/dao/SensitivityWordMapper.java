package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.SensitivityWord;

public interface SensitivityWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SensitivityWord record);

    int insertSelective(SensitivityWord record);

    SensitivityWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SensitivityWord record);

    int updateByPrimaryKey(SensitivityWord record);
    //查询昵称敏感词
    List<SensitivityWord> querySensitiveName();
}