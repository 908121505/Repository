package com.calf.module.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.impl.SkillService;
import com.calf.module.order.vo.SkillVO;

import shaded.org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/skill")
public class SkillController implements BaseController<SkillVO>{

	@Autowired
	private SkillService  skillService;
	
	@Override
	public String home() {
		return "order/skillList";
	}

	@Override
	public DataTables<SkillVO> initTable(HttpServletRequest request) {
		return skillService.getSkillPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id)){
			skillService.getSkillDetail(model,id);
		}
		return  "order/skillAdd";
	}

	@Override
	public int saveAdd(SkillVO entity) {
		return skillService.saveAdd(entity);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int saveUpdate(SkillVO entity) {
		return skillService.saveUpdate(entity);
	}

	@Override
	public int delete(String id) {
		return 0;
	}

	
}