package com.honglu.quickcall.account.service.dao;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.Aliacount;

public interface AliacountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Aliacount record);

    int insertSelective(Aliacount record);
    
    /**
     * 根据用户ID 查询支付宝信息
     * @param userId
     * @return
     */
    Aliacount selectByPrimaryKey(@Param("userId")String userId);

    int updateByPrimaryKeySelective(Aliacount record);

    int updateByPrimaryKey(Aliacount record);
}