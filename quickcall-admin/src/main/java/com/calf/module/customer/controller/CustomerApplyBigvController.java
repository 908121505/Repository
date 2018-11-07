package com.calf.module.customer.controller;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.CustomerApplyBigv;
import com.honglu.quickcall.common.api.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通客户申请大vController
 * @Auther: Sunju
 * @Date: 2018/11/7 15:25
 */
@Controller
@RequestMapping("/customerApplyBigv")
public class CustomerApplyBigvController implements BaseController<CustomerApplyBigv> {

	@Autowired
	private BaseManager baseManager;

	@Override
	public String home() {
		ModelMap modelMap = new ModelMap();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("iDisplayStart", 0);
		parameters.put("iDisplayLength", 100);
		Date curDate = new Date();
		Date sDate = DateUtils.getAddDate(curDate, -60 * 24 * 30);
		String startTime = org.apache.commons.httpclient.util.DateUtil.formatDate(sDate, "yyyy-MM-dd 00:00:00");
		String endTime = org.apache.commons.httpclient.util.DateUtil.formatDate(curDate, "yyyy-MM-dd 23:59:59");
		modelMap.addAttribute("showStartTime",startTime);
		modelMap.addAttribute("showEndTime",endTime);

		List<CustomerApplyBigv> CustomerApplyBigv = baseManager.query(CustomerApplyBigv.class, parameters);
		modelMap.addAttribute("CustomerApplyBigv", CustomerApplyBigv);
		return "customer/bigv_apply/list";
	}

	@Override
	public DataTables<CustomerApplyBigv> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String) parameters.get("sEcho");
		List<CustomerApplyBigv> CustomerApplyBigv = baseManager.query(CustomerApplyBigv.class, parameters);
		int total = baseManager.get("CustomerApplyBigv.queryCount", parameters);
		return new DataTables<CustomerApplyBigv>(sEcho, CustomerApplyBigv, CustomerApplyBigv.size(), total);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if (StringUtils.isNotBlank(id)) {
			model.addAttribute("entity", baseManager.get("CustomerApplyBigv.getApplyBigvById", new Object[]{id}));
		}
		return "customer/bigv_apply/edit";
	}

	@Override
	public int saveAdd(CustomerApplyBigv entity) {
		return 0;
	}


	@Override
	@MethodLog(operType = "删除")
	@RequiresPermissions(value = "customerApplyBigv:delete")
	public int delete(Long id) {
		return baseManager.delete(CustomerApplyBigv.class, new Object[]{id});
	}

	@Override
	@MethodLog(operType = "删除")
	@RequiresPermissions(value = "customerApplyBigv:delete")
	public int delete(String id) {
		return baseManager.delete(CustomerApplyBigv.class, new Object[]{id});
	}

	@Override
	public int saveUpdate(CustomerApplyBigv entity) {
		Subject currentUser = SecurityUtils.getSubject();
		entity.setModifyMan((String) currentUser.getPrincipal());
		entity.setModifyTime(DateUtil.dateFormat());
		return baseManager.update(entity);
	}

}
