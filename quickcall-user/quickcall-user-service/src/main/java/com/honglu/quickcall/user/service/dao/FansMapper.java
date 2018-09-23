package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.Fans;

public interface FansMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fans record);

    int insertSelective(Fans record);

    Fans selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fans record);

    int updateByPrimaryKey(Fans record);
    //根据customerId查询粉丝数量
    Long queryFansNumByCustomerId(Long customerId);
}