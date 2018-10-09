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
    //根据customerId查询粉丝数量
    Long queryFansNumByCustomerId(Long customerId);
    //根据customerId查询关注数量 
    int queryAttentionNumByCustomerId(Long customerId);

    /**
     * 查询关注列表
     * @param customerId
     * @return
     */
	List<AttentionFansVO> queryAttentionListByCustomerId(Long customerId);

	/**
	 * 查询粉丝列表
	 * @param customerId
	 * @return
	 */
	List<AttentionFansVO> queryFansListByCustomerId(Long customerId);

	/**
	 * 判断关注记录
	 * @param fansId
	 * @param attendedId
	 * @return
	 */
	Fans queryFans(@Param("fansId") Long fansId, @Param("attendedId")  Long attendedId);
}