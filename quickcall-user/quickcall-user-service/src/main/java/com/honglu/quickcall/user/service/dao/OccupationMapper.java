package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.Occupation;

public interface OccupationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Occupation record);

    int insertSelective(Occupation record);

    Occupation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Occupation record);

    int updateByPrimaryKey(Occupation record);
    
    //根据customerId查找职业
    List selectByCustomerId(Long customerId);
}