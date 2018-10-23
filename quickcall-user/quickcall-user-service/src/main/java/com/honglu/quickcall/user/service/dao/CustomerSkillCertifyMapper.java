package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerSkillCertify;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * CustomerSkillCertifyMapper数据库操作接口类bean
 * @author zhaozheyi
 * @date Fri Oct 19 11:38:50 CST 2018
 **/

public interface CustomerSkillCertifyMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CustomerSkillCertify  getEntityById ( @Param("id") Integer id );

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CustomerSkillCertify>  findAll ( @Param("id") Integer id );
	
	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CustomerSkillCertify>  selectAllSkillByCustomer ( @Param("customerId") Long customerId );
	
	/**
	 * 
	 * 查询主播技能认证
	 * 
	 **/
	CustomerSkillCertify  selectSkillCertifyId ( @Param("customerId") Long customerId ,@Param("skillItemId") Long skillItemId );
	
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
	int saveEntity( CustomerSkillCertify record );

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity( CustomerSkillCertify record );

}