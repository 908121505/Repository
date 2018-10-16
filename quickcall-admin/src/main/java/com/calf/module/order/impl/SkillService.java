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
import com.calf.module.order.vo.SkillVO;
import com.honglu.quickcall.common.core.util.UUIDUtils;

@Service("skillService")
public class SkillService {

	@Autowired
	private BaseManager  baseManager;
	@Autowired
	private CommonUtilService  commonUtilService ;

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
		model.addAttribute("entity", skill);
	}

	public int saveAdd(SkillVO entity) {
		//根据名称获取公司信息
		Skill  skill =  new Skill();
		BeanUtils.copyProperties(entity, skill);
		Long  id = UUIDUtils.getId();
		skill.setId(id+"");
		skill.setCreateMan(commonUtilService.getCurrUser());
		skill.setSkillStatus(0);//有效
		skill.setCreateTime(new Date());
		baseManager.insert(skill);
		return 0;
	}

	public int saveUpdate(SkillVO entity) {
		Skill  skill =  new Skill();
		BeanUtils.copyProperties(entity, skill);
		skill.setModifyMan(commonUtilService.getCurrUser());
		skill.setModifyTime(new Date());
		baseManager.update(skill);
		return 0;
	}

	
	
	
	

	
}