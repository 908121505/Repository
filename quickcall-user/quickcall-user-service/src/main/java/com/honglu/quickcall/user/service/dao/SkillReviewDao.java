package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.SkillReview;
import org.apache.ibatis.annotations.Param;

/**
 * SkillReviewServiceDao数据库操作接口类bean
 * @author zhaozheyi
 * @date Thu Oct 18 14:40:43 CST 2018
 **/

public interface SkillReviewDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SkillReview  selectByPrimaryKey ( @Param("id") Long id );

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey ( @Param("id") Long id );

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert( SkillReview record );

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective( SkillReview record );

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective( SkillReview record );

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey ( SkillReview record );

}