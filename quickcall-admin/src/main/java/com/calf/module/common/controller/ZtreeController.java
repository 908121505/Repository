package com.calf.module.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.service.BaseManager;
import com.calf.module.common.entity.Ztree;

@Controller
@RequestMapping("/tree")
public class ZtreeController {
	@Autowired
	private BaseManager baseManager;

	/**
	 * 查询栏位树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/column.htm")
	public List<Ztree> column_tree(){
		return baseManager.query("ZTree.queryViewColumnTree", null);
	}
	
	/**
	 * 查询所有栏位树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/columns.htm")
	public List<Ztree> all_column_tree(){
		return baseManager.query("ZTree.queryViewColumnsTree", null);
	}
	
	/**
	 * 查询所有栏位树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/column/poster.htm")
	public List<Ztree> all_column_poster(){
		return baseManager.query("ZTree.queryViewPosterTree", null);
	}
	
	/**
	 * 查询栏目树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/columndata/radio.htm")
	public List<Ztree> column_data_tree(HttpServletRequest request){
		String fieldId = request.getParameter("fieldId");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fieldid", fieldId);
		return baseManager.query("ZTree.queryViewColumnDataRadioTree", params);
	}
	
	/**
	 * 查询栏目树父级菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/column/data.htm")
	public List<Ztree> column_data_tree(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("is_parent",1);
		return baseManager.query("ZTree.queryViewColumnDataTree", params);
	}
	
	/**
	 * 
	 * 查询栏目树栏目专区属性
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/column/alldata.htm")
	public List<Ztree> column_alldata_tree(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ctype",4);
		return baseManager.query("ZTree.queryViewColumnDataTree", params);
	}
	/**
	 * 查询菜单树父级菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/initTree.htm")
	public List<Ztree> initTree(){
		return baseManager.query("ZTree.queryMenuParent", null);
	}
}
