package com.calf.module.order.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.impl.SkillItemService;
import com.calf.module.order.impl.SkillService;
import com.calf.module.order.vo.SkillItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shaded.org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/skill")
public class SkillController implements BaseController<SkillItemVo>{

	@Autowired
	private SkillService  skillService;

	@Autowired
	private SkillItemService skillItemService;
	
	@Override
	public String home() {

		return "order/skillList";
	}

	@Override
	public DataTables<SkillItemVo> initTable(HttpServletRequest request) {
		return skillItemService.getSkillPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		System.out.println(id);
		if(StringUtils.isNotBlank(id)){
			skillItemService.getSkillItemDetail(model,id);
		}
		return  "order/skillAdd";
	}

	@Override
	public int saveAdd(SkillItemVo entity) {
		return skillItemService.saveAdd(entity);
	}

	@Override
	public int delete(Long id) {
		return skillItemService.delete(id);
	}

	@Override
	public int saveUpdate(SkillItemVo entity) {
		return skillItemService.saveUpdate(entity);
	}

	@Override
	public int delete(String id) {
		return skillItemService.delete(id);
	}

/*	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id)){
			skillService.getSkillDetail(model,id);
		}
		return  "order/skillAdd";
	}


	public int saveAdd(SkillVO entity) {
		return skillService.saveAdd(entity);
	}

	@Override
	public int delete(Long id) {
		skillService.delete(id+"");
		return 0;
	}


	public int saveUpdate(SkillVO entity) {
		return skillService.saveUpdate(entity);
	}

	@Override
	public int delete(String id) {
		skillService.delete(id);
		return 0;
	}*/



}