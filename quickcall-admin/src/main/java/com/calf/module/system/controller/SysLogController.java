package com.calf.module.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.system.entity.SysLog;

@Controller
@RequestMapping(value="/system/log")
public class SysLogController implements BaseController<SysLog> {
	
	@Autowired
	private BaseManager baseManager;

	@Override
	public String home() {
		// TODO Auto-generated method stub
		return "system/log/loglist";
	}

	@Override
	@SuppressWarnings("unchecked")
	public DataTables<SysLog> initTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String)parameters.get("sEcho");
		
		List<SysLog> aaData = baseManager.query(SysLog.class, parameters);
		Integer iTotalDisplayRecords = baseManager.get("SysLog.queryCount", parameters);
		
		return new DataTables<SysLog>(sEcho, aaData, aaData.size(), iTotalDisplayRecords);
	}

	@Override
	@RequiresPermissions(value="log:detail")
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("entity", baseManager.get(SysLog.class, new Object[]{id}));
		}
		return "system/log/addLog";
	}

	@Override
	public int saveAdd(SysLog entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequiresPermissions(value="log:delete")
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return baseManager.delete(SysLog.class, new Object[]{id});
	}

	@Override
	public int saveUpdate(SysLog entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (非 Javadoc)
	 * Description:
	 * @see com.calf.cn.controller.BaseController#delete(java.lang.String)
	 */
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
