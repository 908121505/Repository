package com.honglu.quickcall.user.service.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.web.PageableDefault;

import com.honglu.quickcall.user.facade.entity.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    Customer login(Customer record);
    
    int customerSetPwd(@Param("phone")String phone,@Param("passWord")String passWord);
    
    int customerSetHeardUrl(@Param("phone")String phone,@Param("headPortraitUrl")String headPortraitUrl
    		,@Param("nickName")String nickName);
}