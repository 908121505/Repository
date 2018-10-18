package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.SkillReview;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * SkillReviewMapper数据库操作接口类bean
 * @author zhaozheyi
 * @date Thu Oct 18 18:02:10 CST 2018
 **/

public interface SkillReviewMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SkillReview  getEntityById ( @Param("customerId") Long customerId );

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<SkillReview>  findAll ( @Param("customerId") Long customerId );

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity ( @Param("customerId") Long customerId );

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity( SkillReview record );

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity( SkillReview record );

}