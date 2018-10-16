package com.honglu.quickcall.user.service.dao;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.in.HomePageLogout;

public interface CustomerMapper {
	int deleteByPrimaryKey(Long customerId);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(Long customerId);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);

	Customer login(Customer record);

	int customerSetPwd(@Param("phone") String phone, @Param("passWord") String passWord);

	int customerSetHeardUrl(@Param("phone") String phone, @Param("headPortraitUrl") String headPortraitUrl,
			@Param("nickName") String nickName);

	/**
	 * 更新性别,生日
	 * 
	 * @param customer
	 * @return
	 */
	int updateGenderByCustomerID(Customer customer);

	/**
	 * 大V主页，普通用户主页（客态）
	 */
	HomePageLogout showHomePageLogout(Long customerId);

	/**
	 * 查询用户身份证认证信息
	 * 
	 * @param customerId
	 * @return
	 */
	Customer queryUserIdCardCertificationInfo(Long customerId);

}