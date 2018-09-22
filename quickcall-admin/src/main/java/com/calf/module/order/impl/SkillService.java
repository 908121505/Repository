package com.calf.module.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.cn.utils.UUIDUtils;
import com.calf.module.order.entity.Skill;
import com.calf.module.order.vo.SkillVO;

@Service("skillService")
public class SkillService {

	@Autowired
	private BaseManager  baseManager;

	@SuppressWarnings("unchecked")
	public DataTables<SkillVO> getSkillPageList(HttpServletRequest request) {
		
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", parameters.get("name"));
		paramMap.put("skillStatus", parameters.get("skillStatus"));
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<SkillVO> skillList = baseManager.query("Skill.selectPageList", paramMap);
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Skill.slectCount", paramMap);
		return new DataTables<SkillVO>(sEcho, skillList, skillList.size(), total);
	}

	public void getSkillDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Skill  skill = baseManager.get("Skill.selectByPrimaryKey",  paramMap);
		model.addAttribute("skill", skill);
	}

	public int saveAdd(SkillVO entity) {
		//根据名称获取公司信息
		
		Skill  skill =  new Skill();
		
		UUIDUtils.getUUID();
		
		
		
		return 0;
	}

	public int saveUpdate(SkillVO entity) {
		return 0;
	}

	
	
	
	

	
}