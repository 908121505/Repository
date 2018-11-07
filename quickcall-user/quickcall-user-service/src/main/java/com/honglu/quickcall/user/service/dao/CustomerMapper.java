package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.vo.SearchPersonByPhoneVO;
import com.honglu.quickcall.user.facade.vo.SearchPersonListVO;
import com.honglu.quickcall.user.facade.vo.UserEditInfoVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

	/**
	 * 查询客户的一个订单（仅查询部分字段）
	 * @param orderId
	 * @return
	 */
	Order selectCustomerOrder(@Param("orderId") Long orderId);

	/**
	 * 更新客户的经验值和等级
	 * @param customerId
	 * @param experience
	 * @return
	 */
	int updateCustomerExperienceAndLevel(@Param("customerId") Long customerId, @Param("experience") Integer experience);

	/**
	 * 查询等级累计经验值
	 * @param customerLevel
	 * @return
	 */
	Integer getGradeExperienceByLevelNo(@Param("customerLevel") Integer customerLevel);


	/**
	 * 编辑资料页面 查询用户数据
	 * @param customerId
	 * @return
	 */
	UserEditInfoVO queryUserEditInfo(@Param("customerId") Long customerId);

	/**
	 * 根据客户ID查客户信息
	 * @param customerId
	 * @return
	 */
	Customer queryCustomerByCustomerId(@Param("customerId") Long customerId);

	/**
	 * 判断客户是否为声优
	 * @param customerId
	 * @return 1=是；0=否
	 */
	int judgeCustomerIsBigv(@Param("customerId") Long customerId);
	
	/**
	 * 根据电话查询用户信息
	 * @param phone
	 * @return
	 */
	List<SearchPersonByPhoneVO> queryPersonByPhone(@Param("phone") Long phone);
}