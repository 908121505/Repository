package com.calf.module.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.impl.SkillPriceLevelService;
import com.calf.module.order.vo.SkillPriceLevelVO;

/**
 * 阶梯价格管理
 * @author zhaozheyi
 *
 */
@Controller
@RequestMapping("/skillPriceLevel")
public class SkillPriceLevelController implements BaseController<SkillPriceLevelVO>{
	
	@Autowired
	private SkillPriceLevelService skillPriceLevelService;
	
	@Override
	public String home() {
		return "order/skillPriceLevelList";
	}

	@Override
	public DataTables<SkillPriceLevelVO> initTable(HttpServletRequest request) {
		return skillPriceLevelService.getSkillPriceLevelPageList(request);
	}
	
	//通过技能ID和价格等级来确定更新操作
	@RequestMapping(value = "/addAndUpdateOther.htm", method = RequestMethod.GET)
	public String addAndUpdate(Model model,@Param(value = "skillItemId") String skillItemId,@Param(value = "priceLevel") String priceLevel){
		skillPriceLevelService.getSkillPriceLevelDetail(model,skillItemId,priceLevel);
		return "order/skillPriceLevelAdd";
	}
	
	//删除该等级
	@ResponseBody
	@RequestMapping(value = "/deleteRow.htm", method = RequestMethod.POST)
	public int deleteRow(@Param(value = "skillItemId") String skillItemId,@Param(value = "priceLevel") String priceLevel){
		return skillPriceLevelService.deleteAll(skillItemId,priceLevel);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPriceLevelExists.htm", method = RequestMethod.POST)
	public int getPriceLevelExists(HttpServletRequest request){
		return skillPriceLevelService.getPriceLevelExists(request);
	}
	
	@Override
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public int saveAdd(SkillPriceLevelVO entity) {
		return skillPriceLevelService.insertPriceLevel(entity);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transactional
	@Override
	public int saveUpdate(SkillPriceLevelVO entity) {
		return skillPriceLevelService.updatePriceLevel(entity);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
