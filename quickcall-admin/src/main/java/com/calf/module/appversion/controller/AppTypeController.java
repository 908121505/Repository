package com.calf.module.appversion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.cn.utils.UUIDUtils;
import com.calf.module.appversion.entity.AppType;

@Controller
@RequestMapping("/apptype")
public class AppTypeController implements BaseController<AppType> {

	@Autowired
	private BaseManager baseManager;

	@Override
	public String home() {
		ModelMap modelMap = new ModelMap();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("iDisplayStart", 0);
		parameters.put("iDisplayLength", 100);
		List<AppType> appType = baseManager.query(AppType.class, parameters);
		modelMap.addAttribute("apptype", appType);
		return "apptype/appTypeList";
	}

	@Override
	public DataTables<AppType> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String) parameters.get("sEcho");
		List<AppType> appType = baseManager.query(AppType.class, parameters);
		int total = baseManager.get("AppType.queryCount", parameters);
		return new DataTables<AppType>(sEcho, appType, appType.size(), total);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if (StringUtils.isNotBlank(id)) {
			model.addAttribute("entity", baseManager.get("AppType.getAppTypeById", new Object[] { id }));
		}
		return "apptype/addAppType";
	}

	@Override
	@MethodLog(operType = "新增")
	@RequiresPermissions(value = "apptype:add")
	public int saveAdd(AppType entity) {
		Subject currentUser = SecurityUtils.getSubject();
		entity.setCreateMan((String) currentUser.getPrincipal());
		entity.setCreateTime(DateUtil.dateFormat());
		entity.setAppTypeId(UUIDUtils.getUUID());
		return baseManager.insert(entity);
	}

	@Override
	@MethodLog(operType = "删除")
	@RequiresPermissions(value = "apptype:delete")
	public int delete(Long id) {
		return baseManager.delete(AppType.class, new Object[] { id });
	}

	@Override
	@MethodLog(operType = "删除")
	@RequiresPermissions(value = "apptype:delete")
	public int delete(String id) {
		return baseManager.delete(AppType.class, new Object[] { id });
	}

	@Override
	public int saveUpdate(AppType entity) {
		Subject currentUser = SecurityUtils.getSubject();
		entity.setModifyMan((String) currentUser.getPrincipal());
		entity.setModifyTime(DateUtil.dateFormat());
		return baseManager.update(entity);
	}

	@RequestMapping("/getAllAppType")
	@ResponseBody
	public Object getAllAppType(HttpServletRequest request) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("iDisplayStart", 0);
		parameters.put("iDisplayLength", 100);
		List<AppType> appType = baseManager.query(AppType.class, parameters);
		return appType;
	}
}
