package com.honglu.quickcall.user.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.in.HomePageLogout;
import com.honglu.quickcall.user.facade.vo.SearchPersonListVO;

public interface CustomerMapper {
	int deleteByPrimaryKey(Long customerId);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(Long customerId);

	Customer selectByAppId(@Param("appId") String appId);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);

	Customer login(Customer record);

	int customerSetPwd(@Param("phone") String phone, @Param("passWord") String passWord);

	int customerSetHeardUrl(@Param("phone") String phone, @Param("headPortraitUrl") String headPortraitUrl,
			@Param("nickName") String nickName, @Param("sex") Integer sex);

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

	/**
	 * 根据关键字模糊搜索
	 * 
	 * @param keyword
	 * @param limitCount
	 * @return
	 */
	List<SearchPersonListVO> selectFuzzySearch(@Param("keyword") String keyword, @Param("customerId") Long customerId,
			@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	/**
	 * 根据关键字精准搜索
	 * 
	 * @param keyword
	 * @param limitCount
	 * @return
	 */
	List<SearchPersonListVO> selectPreciseSearch(@Param("keyword") String keyword,
			@Param("customerId") Long customerId);

	int selectCountByNickNameAndId(@Param("nickName") String nickName, @Param("customerId") String customerId);
}