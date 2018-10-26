package com.calf.module.resource.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.resource.entity.ResourceConfigExCust;
import com.calf.module.resource.entity.ResourceConfigSkill;
import com.calf.module.resource.entity.ResourcePool;
import com.calf.module.resource.vo.ResourceConfigVO;

import shaded.org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/resourceConfig")
public class ResourceConfigController implements BaseController<ResourceConfigVO> {
	@Autowired
    private BaseManager baseManager;
	
	private static final String JSP_PATH = "resource/%s";

	@Override
	public String home() {
		return String.format(JSP_PATH, "resourceConfigList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataTables<ResourceConfigVO> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		 String sEcho = (String) parameters.get("sEcho");
        List<ResourceConfigVO> banners = baseManager.query("ResourceConfigMapper.queryResourceConfigList", parameters);
        int total = baseManager.get("ResourceConfigMapper.queryCount", parameters);
        for (ResourceConfigVO resourcesConfigVO : banners) {
        	Map<String, Object> map = new HashMap<>();
        	map.put("resourceConfigId", resourcesConfigVO.getResourceConfigId());
        	map.put("resourcePoolId", resourcesConfigVO.getResourcePoolId());
			int tot = baseManager.get("ResourceConfigMapper.queryTotalCus", map);
			map.put("resourceConfigId", resourcesConfigVO.getResourceConfigId());
        	map.put("resourcePoolId", resourcesConfigVO.getResourcePoolId());
			int rec = baseManager.get("ResourceConfigMapper.queryReceiptCus", map);
			map.put("resourceConfigId", resourcesConfigVO.getResourceConfigId());
        	map.put("resourcePoolId", resourcesConfigVO.getResourcePoolId());
			String skillName = baseManager.get("ResourceConfigMapper.querySkillItemList", map);
			resourcesConfigVO.setTotalCusNum(tot);
			resourcesConfigVO.setReceiptCusNum(rec);
			resourcesConfigVO.setSurplusCusNum(tot-rec);
			resourcesConfigVO.setSkillName(skillName);
		}
        return new DataTables<>(sEcho, banners, banners.size(), total);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		List<SkillItem> skillItemList = baseManager.query("ResourceConfigMapper.querySkillItemSelect", null);
		List<ResourcePool> resourcePoolList = baseManager.query("ResourceConfigMapper.queryResourcePoolSelect", null);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("resourceConfigId", id);
		ResourceConfigVO ResourceConfigVO = baseManager.get("ResourceConfigMapper.queryResourceConfigById", parameters);
		parameters.put("resourceConfigId", id);
		String rcs = baseManager.get("ResourceConfigMapper.queryResourceConfigSkillById",parameters);
		parameters.put("resourceConfigId", id);
		ResourceConfigVO.setResourceConfigSkillList(rcs);
		List<String> rcec = baseManager.query("ResourceConfigMapper.queryResourceConfigExCustById",parameters);
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<rcec.size();i++) {
			sb.append(rcec.get(i));
			if(i<rcec.size()-1){
				sb.append("\n");
			}
		}
		
		
		ResourceConfigVO.setExCusList(sb.toString());
		model.addAttribute("entity", ResourceConfigVO);
		model.addAttribute("skillItemList", skillItemList);
		model.addAttribute("resourcePoolList", resourcePoolList);
		return String.format(JSP_PATH, "resourceConfigEdit");
	}

	@Override
	public int saveAdd(ResourceConfigVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int saveUpdate(ResourceConfigVO entity) {
		String resourceConfigSkillList = entity.getResourceConfigSkillList();
		String exCusList = entity.getExCusList();
		String[] skillList = new String[]{};
		String[] custList = new String[]{};
		if(StringUtils.isNotBlank(resourceConfigSkillList)){
			skillList = resourceConfigSkillList.split(",");
		}
		if(StringUtils.isNotBlank(exCusList)){
			custList = exCusList.split("\n");
		}
		List<Map<String,Object>> insertSkillList = new ArrayList<>();
		List<Map<String,Object>> insertCustList = new ArrayList<>();
		for (String skill : skillList) {
			Map<String,Object> skillMap = new HashMap<>();
			skillMap.put("skillItemId", skill);
			skillMap.put("resourceConfigId", entity.getResourceConfigId());
			insertSkillList.add(skillMap);
		}
		for (String cust : custList) {
			Map<String,Object> custMap = new HashMap<>();
			custMap.put("appId", cust);
			custMap.put("resourceConfigId", entity.getResourceConfigId());
			insertCustList.add(custMap);
		}
		baseManager.delete("ResourceConfigMapper.deleteResourceConfigSkill", new Object[]{entity.getResourceConfigId()});
		baseManager.delete("ResourceConfigMapper.deleteResourceConfigExCust", new Object[]{entity.getResourceConfigId()});
		baseManager.update("ResourceConfigMapper.updateEntity",entity);
		baseManager.batchInsert("ResourceConfigMapper.insertBatchResourceConfigSkill", insertSkillList);
		baseManager.batchInsert("ResourceConfigMapper.insertBatchResourceConfigExCust", insertCustList);
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
