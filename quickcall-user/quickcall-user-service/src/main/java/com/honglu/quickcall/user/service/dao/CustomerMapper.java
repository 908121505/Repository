package com.honglu.quickcall.user.service.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.vo.SearchPersonListVO;

public interface CustomerMapper {

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(Long customerId);

	Customer selectByAppId(@Param("appId") String appId);

	int updateByPrimaryKeySelective(Customer record);

	Customer login(Customer record);

	int customerSetPwd(@Param("phone") String phone, @Param("passWord") String passWord);

	int customerSetHeardUrl(@Param("phone") String phone, @Param("headPortraitUrl") String headPortraitUrl,
			@Param("nickName") String nickName, @Param("sex") Integer sex);

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
	 * @return
	 */
	List<SearchPersonListVO> selectFuzzySearch(@Param("keyword") String keyword, @Param("customerId") Long customerId,
			@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	/**
	 * 根据关键字精准搜索
	 * 
	 * @param keyword
	 * @return
	 */
	List<SearchPersonListVO> selectPreciseSearch(@Param("keyword") String keyword,
			@Param("customerId") Long customerId);

	int selectCountByNickNameAndId(@Param("nickName") String nickName, @Param("customerId") String customerId);

	/**
	 * 查询客户的充值金额 + 提现金额
	 * @param customerId
	 * @return rechargeAmounts 充值金额；withdrawAmounts 提现金额
	 */
    Map<String, BigDecimal> queryCustomerAccountMoney(@Param("customerId") Long customerId);
}