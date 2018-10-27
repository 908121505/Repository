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
	 * 查询所有技能
	 **/
	List<SkillItem>  selectAllSkill ();

	/**
	 * 查询所有可用的技能
	 * @return
	 */
	List<SkillItem> selectAllEnabledSkills();
}