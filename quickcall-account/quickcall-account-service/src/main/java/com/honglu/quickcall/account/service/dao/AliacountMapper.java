package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.Aliacount;

public interface AliacountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Aliacount record);

    int insertSelective(Aliacount record);

    Aliacount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Aliacount record);

    int updateByPrimaryKey(Aliacount record);
}