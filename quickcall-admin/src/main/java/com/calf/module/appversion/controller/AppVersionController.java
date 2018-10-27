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
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.cn.utils.UUIDUtils;
import com.calf.module.appversion.entity.AppType;
import com.calf.module.appversion.entity.AppVersionManage;

@Controller
@RequestMapping("/appversion")
public class AppVersionController implements BaseController<AppVersionManage> {

	@Autowired
	private BaseManager baseManager;

	@Override
	public String home() {
		// TODO Auto-generated method stub
		return "appver/appversion/appVersionlist";
	}

	@Override
	public DataTables<AppVersionManage> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String) parameters.get("sEcho");
		List<AppVersionManage> appVersion = baseManager.query(AppVersionManage.class, parameters);
		for (AppVersionManage appVersion2 : appVersion) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("appTypeCode", appVersion2.getAppType());
			List<AppType> appType = baseManager.query("AppType.getAppTypeByCode", param);
			if (appType != null && appType.size() > 0) {

				appVersion2.setAppTypeName(appType.get(0).getAppTypeName());
			}
		}

		int total = baseManager.get("AppVersionManage.queryCount", parameters);
		return new DataTables<AppVersionManage>(sEcho, appVersion, appVersion.size(), total);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if (StringUtils.isNotBlank(id)) {
			model.addAttribute("entity", baseManager.get("AppVersionManage.getAppVersionById", new Object[] { id }));
		}
		return "appver/appversion/addAppVersion";
	}

	@Override
	@MethodLog(operType = "新增")
	@RequiresPermissions(value = "appversion:add")
	public int saveAdd(AppVersionManage entity) {
		Subject currentUser = SecurityUtils.getSubject();
		entity.setCreateMan((String) currentUser.getPrincipal());
		entity.setCreateTime(DateUtil.dateFormat());
		entity.setAppVersionId(UUIDUtils.getUUID());
		if (StringUtils.isBlank(entity.getBeginTime())) {
			entity.setBeginTime(null);
		}
		if (StringUtils.isBlank(entity.getEndTime())) {
			entity.setEndTime(null);
		}
		return baseManager.insert(entity);
	}

	@Override
	@MethodLog(operType = "删除")
	@RequiresPermissions(value = "appversion:delete")
	public int delete(Long id) {
		return baseManager.delete(AppVersionManage.class, new Object[] { id });
	}

	@Override
	@MethodLog(operType = "删除")
	@RequiresPermissions(value = "appversion:delete")
	public int delete(String id) {
		return baseManager.delete(AppVersionManage.class, new Object[] { id });
	}

	/*
	 * (非 Javadoc) Description:
	 * 
	 * @see com.calf.cn.controller.BaseController#saveUpdate(java.lang.Object)
	 */
	@Override
	public int saveUpdate(AppVersionManage entity) {
		Subject currentUser = SecurityUtils.getSubject();
		entity.setModifyMan((String) currentUser.getPrincipal());
		entity.setModifyTime(DateUtil.dateFormat());
		return baseManager.update(entity);
	}
}
