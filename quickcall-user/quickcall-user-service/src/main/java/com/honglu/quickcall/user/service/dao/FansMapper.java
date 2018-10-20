package com.honglu.quickcall.user.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.Fans;
import com.honglu.quickcall.user.facade.vo.AttentionFansVO;

public interface FansMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Fans record);

	int insertSelective(Fans record);

	Fans selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Fans record);

	int updateByPrimaryKey(Fans record);

	// 根据customerId查询粉丝数量
	Long queryFansNumByCustomerId(Long customerId);

	// 根据customerId查询关注数量
	int queryAttentionNumByCustomerId(Long customerId);

	/**
	 * 判断是否关注
	 * 
	 * @param anchorId
	 * @param fansId
	 * @return
	 */
	int queryIsFollow(@Param("anchorId") Long anchorId, @Param("fansId") Long fansId);

	/**
	 * 查询关注列表
	 * 
	 * @param customerId
	 * @return
	 */
	List<AttentionFansVO> queryAttentionListByCustomerId(@Param("customerId") Long customerId,
			@Param("attentionStatus") Integer attentionStatus);

	/**
	 * 查询粉丝列表
	 * 
	 * @param customerId
	 * @return
	 */
	List<Long> queryFansIdListByCustomerId(@Param("customerId") Long customerId,
			@Param("attentionStatus") Integer attentionStatus);

	/**
	 * 根据客户ID列表查询客户列表
	 * 
	 * @param customerIdList
	 * @return
	 */
	List<AttentionFansVO> queryCustomerListByCustomerIdList(@Param("list") List<Long> customerIdList);

	/**
	 * 查询粉丝列表
	 * 
	 * @param fansId
	 * @param customerId
	 * @return
	 */
	List<Fans> queryFansListByFansIdList(@Param("list") List<Long> fansId, @Param("customerId") Long customerId);

	/**
	 * 查询粉丝列表
	 * 
	 * @param customerId
	 * @return
	 */
	List<AttentionFansVO> queryFansListByCustomerId(@Param("customerId") Long customerId,
			@Param("attentionStatus") Integer attentionStatus);

	/**
	 * 判断关注记录
	 * 
	 * @param fansId
	 * @param attendedId
	 * @return
	 */
	Fans queryFans(@Param("fansId") Long fansId, @Param("attendedId") Long attendedId);

	/**
	 * 查询关注情况
	 * 
	 * @param userId
	 * @param customerId
	 * @return
	 */
	Fans queryAttentionInfo(@Param("userId") Long userId, @Param("customerId") Long customerId);

	/**
	 * 阅读关注
	 * 
	 * @param customerId
	 * @return
	 */
	int updateReadAttention(@Param("customerId") Long customerId);

}