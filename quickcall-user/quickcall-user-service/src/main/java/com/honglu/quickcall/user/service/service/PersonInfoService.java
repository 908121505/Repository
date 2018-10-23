package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.*;

/**
 * 
 * @author liuyinkai
 *
 */
public interface PersonInfoService {

	/**
	 * 保存昵称/头像
	 */
	CommonResponse saveNicknameImage(SaveNickNameRequest params);

	/**
	 * 保存性别
	 */
	CommonResponse saveGender(SaveGenderRequest params);

	/**
	 * 保存签名
	 */
	CommonResponse saveSignName(SaveSignNameRequest params);

	/**
	 * 保存生日
	 */
	CommonResponse saveBirthday(SaveBirthRequest params);

	/**
	 * 保存兴趣
	 */
	CommonResponse saveInterest(SaveInterestRequest params);

	/**
	 * 保存职业
	 */
	CommonResponse saveOccupation(SaveOccupationRequest params);

	/** 查询兴趣列表 */
	CommonResponse queryInterestList(QueryInterestListRequest request);

	/** 查询职业列表 */
	CommonResponse queryOccupationList(QueryOccupationListRequest request);

	/** 查询关注/粉丝列表 */
	CommonResponse queryAttentionFansList(QueryAttentionFansListRequest request);

	/** 添加/取消关注 */
	CommonResponse addOrCancelFans(AddOrCancelFansRequest request);

	/** 检查是否关注对方 */
	CommonResponse checkAttention(CheckAttentionRequest request);

	/**
	 * 首页查询用户列表
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse searchPerson(SearchPersonRequest params);

	/**
	 * 阅读关注
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse readAttention(ReadAttentionRequest params);
	
	/**
	 * 首页查询用户列表
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse queryMySkill(queryMyskillRequest params);

	/**
	 * 保存我的技能认证信息
	 *
	 * @param request
	 * @return
	 */
	CommonResponse saveCustomerSkillCertify(SaveSkillAuditRequest request);

	/**
	 * 查询个人中心数据接口
	 *
	 * @param request
	 * @return
	 */
	CommonResponse queryCustomerCenter(CustomerCenterRequest request);

	/**
	 * 查询客户个人主页数据接口
	 * @param request
	 * @return
	 */
	CommonResponse queryCustomerHome(CustomerHomeRequest request);

	/**
	 * 我的等级页面 -- 展示数据接口
	 * @param request
	 * @return
	 */
	CommonResponse queryCustomerLevel(CustomerLevelRequest request);
}
