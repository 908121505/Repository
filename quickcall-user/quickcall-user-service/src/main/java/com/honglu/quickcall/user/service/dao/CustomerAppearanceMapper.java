package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerAppearance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CustomerAppearanceMapper数据库操作接口类bean
 * @author luoyanchong
 * @date Sat Oct 20 14:52:09 CST 2018
 **/

public interface CustomerAppearanceMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CustomerAppearance  getEntityById ( @Param("id") String id );

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CustomerAppearance> findAll (@Param("id") String id );

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
	int saveEntity( CustomerAppearance record );

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity( CustomerAppearance record );

}