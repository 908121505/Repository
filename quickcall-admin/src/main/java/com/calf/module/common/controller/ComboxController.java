/**
 * @author guixin
 *
 */
package com.calf.module.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.service.BaseManager;
import com.calf.module.common.entity.Combobox;

/**
 * 下拉框控制类
 * @author guixin
 *
 */
@Controller
@RequestMapping("/combobox")
public class ComboxController {
	
	@Autowired
	private BaseManager baseManager;	
	
	/**
	 * 查询cp信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cpinfo.htm",method=RequestMethod.POST)
	public List<Combobox> queryCpCombobx(){
		return baseManager.query("Combobox.queryCpInfos", null);
	}
	
	/**
	 * 查询课程下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/courses.htm",method=RequestMethod.POST)
	public List<Combobox> queryCourseCombobx(){
		return baseManager.query("Combobox.queryCourseInfos", null);
	}
	
	/**
	 * 查询课程下单元信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/units.htm",method=RequestMethod.POST)
	public List<Combobox> queryCourseUnitCombobx(String course_code){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("course_code", course_code);
		return baseManager.query("Combobox.queryUnitByCourseCode", params);
	}
	
	/**
	 * 查询课程下单元信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/wares.htm",method=RequestMethod.POST)
	public List<Combobox> queryCourseWareCombobx(String unit_code){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("unit_code", unit_code);
		return baseManager.query("Combobox.queryWareByUnitCode", params);
	}
	
	/**
	 * 查询产品下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/products.htm",method=RequestMethod.POST)
	public List<Combobox> queryProductCombobx(){
		return baseManager.query("Combobox.queryProductInfos", null);
	}
	
	/**
	 * 查询栏位下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/column.htm",method=RequestMethod.POST)
	public List<Combobox> queryColumnCombobx(){
		return baseManager.query("Combobox.queryColumnInfos", null);
	}
	
	/**
	 * 查询父级栏位下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/parent/column.htm",method=RequestMethod.POST)
	public List<Combobox> queryParentColumnCombobx(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("is_parent", 1);
		return baseManager.query("Combobox.queryColumnInfos", params);
	}
	
	/**
	 * 查询课程模板类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/course/temps.htm",method=RequestMethod.POST)
	public List<Combobox> queryCourseTempCombobx(){
		return baseManager.query("Combobox.queryCourseTempType", null);
	}
	
	/**
	 * 查询栏目数据下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/column/data.htm",method=RequestMethod.POST)
	public List<Combobox> queryColumnDataCombobx(){
		return baseManager.query("Combobox.queryColumnDataInfos", null);
	}
	
	/**
	 * 查询父级栏目数据下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/column/parent/data.htm",method=RequestMethod.POST)
	public List<Combobox> queryParentColumnDataCombobx(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("is_parent", 1);
		return baseManager.query("Combobox.queryColumnDataInfos", params);
	}
	
	/**
	 * 查询栏目专区属性下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/column/lzs/data.htm",method=RequestMethod.POST)
	public List<Combobox> queryLZSDataCombobx(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ctype",4);
		return baseManager.query("Combobox.queryColumnDataInfos", params);
	}
	
	
	
	/**
	 * 查询海报数据下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/column/poster.htm",method=RequestMethod.POST)
	public List<Combobox> queryColumnPosterCombobx(){
		return baseManager.query("Combobox.queryColumnPoster", null);
	}
	
	/**
	 * 查询海报数据下拉信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendUser.htm",method=RequestMethod.POST)
	public List<Combobox> findSendUser(){
		Map<String,Object> parameters = new HashMap<String ,Object>() ;
		parameters.put("author", "message:add") ;
		return baseManager.query("Combobox.queryUsersByAuthor", parameters);
	}
}