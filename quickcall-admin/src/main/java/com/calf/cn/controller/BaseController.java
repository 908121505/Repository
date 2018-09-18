package com.calf.cn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.entity.DataTables;

/**
 * 控制类基础接口，实线增删改查统一
 * @author zhoujian
 *
 */
public interface BaseController<T> {
	
	/**
	 * 跳转到初始化页面
	 * @return
	 */
	@RequestMapping(value = "/home.htm")
	public String home();
	
	/**
	 * 初始化表格数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/initTable.htm")
	public DataTables<T> initTable(HttpServletRequest request);
	
	/**
	 * 跳转增加和删除页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateHome.htm",method = RequestMethod.GET)
	public String addAndUpdateHome(Model model,String id);
	
	/**
	 * 增加记录
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveInsert.htm",method = RequestMethod.POST)
	public int saveAdd(T entity);
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.htm",method = RequestMethod.POST)
	public int delete(Long id);
	
	/**
	 * 修改记录
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveUpdate.htm",method = RequestMethod.POST)
	public int saveUpdate(T entity);

	/**
	 * @Description Description
	 * @param id
	 * @return
	 */
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del.htm",method = RequestMethod.POST)
	public int delete(String id);

}
