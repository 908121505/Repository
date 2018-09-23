package com.calf.module.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.order.entity.Skill;
import com.calf.module.order.vo.ProductVO;
import com.honglu.quickcall.common.core.util.UUIDUtils;

@Service("productService")
public class ProductService {

	@Autowired
	private BaseManager  baseManager;
	@Autowired
	private CommonUtilService  commonUtilService ;

	@SuppressWarnings("unchecked")
	public DataTables<ProductVO> getProductPageList(HttpServletRequest request) {
		
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("servNickName", parameters.get("servNickName"));
		paramMap.put("orderType", parameters.get("orderType"));
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<ProductVO> skillList = baseManager.query("Product.selectPageList", paramMap);
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Product.slectCount", paramMap);
		return new DataTables<ProductVO>(sEcho, skillList, skillList.size(), total);
	}

	public void getProductDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Skill  skill = baseManager.get("Product.selectByPrimaryKey",  paramMap);
		model.addAttribute("entity", skill);
	}

	public int saveAdd(ProductVO entity) {
		//根据名称获取公司信息
		Skill  skill =  new Skill();
		Long  id = UUIDUtils.getId();
		BeanUtils.copyProperties(entity, skill);
		skill.setId(id+"");
		skill.setCreateMan(commonUtilService.getCurrUser());
		skill.setSkillStatus(0);//有效
		skill.setCreateTime(new Date());
		baseManager.insert(skill);
		return 0;
	}

	public int saveUpdate(ProductVO entity) {
		Skill  skill =  new Skill();
		BeanUtils.copyProperties(entity, skill);
		skill.setModifyMan(commonUtilService.getCurrUser());
		skill.setModifyTime(new Date());
		baseManager.update(skill);
		return 0;
	}

	
	
	
	

	
}