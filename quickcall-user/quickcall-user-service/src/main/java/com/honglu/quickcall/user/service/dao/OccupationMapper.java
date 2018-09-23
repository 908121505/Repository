package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.Occupation;

public interface OccupationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Occupation record);

    int insertSelective(Occupation record);

    Occupation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Occupation record);

    int updateByPrimaryKey(Occupation record);
    
    //根据customerId查找职业
    String selectByCustomerId(Long customerId);
}