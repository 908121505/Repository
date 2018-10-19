package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.SkillItem;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * SkillItemMapper数据库操作接口类bean
 * @author zhaozheyi
 * @date Fri Oct 19 17:40:14 CST 2018
 **/

public interface SkillItemMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SkillItem  getEntityById ( @Param("id") String id );

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<SkillItem>  findAll ( @Param("id") String id );

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity ( @Param("id") String id );

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity( SkillItem record );

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity( SkillItem record );

}