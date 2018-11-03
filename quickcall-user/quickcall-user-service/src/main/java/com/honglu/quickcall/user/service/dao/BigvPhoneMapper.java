package com.honglu.quickcall.user.service.dao;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.BigvPhone;

public interface BigvPhoneMapper {

	BigvPhone queryOneByPhone(@Param("phone") String phone);
}