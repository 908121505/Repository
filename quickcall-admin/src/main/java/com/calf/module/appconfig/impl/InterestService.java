package com.calf.module.appconfig.impl;

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
import com.calf.module.appconfig.entity.Interest;
import com.calf.module.common.impl.CommonUtilService;

@Service("interestService")
public class InterestService {

	@Autowired
	private BaseManager  baseManager;
	@Autowired
	private CommonUtilService  commonUtilService ;

	@SuppressWarnings("unchecked")
	public DataTables<Interest> getInterestPageList(HttpServletRequest request) {
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<Interest> interestList = baseManager.query("Interest.selectPageList", paramMap);
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Interest.slectCount", paramMap);
		return new DataTables<Interest>(sEcho, interestList, interestList.size(), total);
	}

	public void getInterestDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Interest  interest = baseManager.get("Interest.selectByPrimaryKey",  paramMap);
		model.addAttribute("entity", interest);
	}

	public int saveAdd(Interest entity) {
		//根据名称获取公司信息
		Interest  interest =  new Interest();
		BeanUtils.copyProperties(entity, interest);
		interest.setCreateMan(commonUtilService.getCurrUser());
		interest.setCreateTime(new Date());
		interest.setType(0);
		baseManager.insert(interest);
		return 0;
	}

	public int saveUpdate(Interest entity) {
		Interest  interest =  new Interest();
		BeanUtils.copyProperties(entity, interest);
		interest.setModifyMan(commonUtilService.getCurrUser());
		interest.setModifyTime(new Date());
		baseManager.update(interest);
		return 0;
	}

	
	
	
	

	
}