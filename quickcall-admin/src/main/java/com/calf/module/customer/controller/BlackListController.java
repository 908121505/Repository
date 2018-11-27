package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.customer.service.BlackListService;
import com.calf.module.customer.vo.BlackListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blackList")
public class BlackListController implements BaseController<BlackListVo>{

	@Autowired
	private BlackListService blackListService;

	private static final String JSP_PATH = "customer/blacklist/%s";

	@Override
	public String home() {
		return String.format(JSP_PATH, "blacklist");
	}

	@Override
	public DataTables<BlackListVo> initTable(HttpServletRequest request) {
		return blackListService.getBlackListPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		/*if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            blackListService.getBlackListDetail(model,id);
		}
		return  String.format(JSP_PATH, "blacklistAdd");*/
		return null;
	}

	@Override
	public int saveAdd(BlackListVo entity) {
	    //return blackListService.saveAdd(entity);
        return 0;
	}

	@Override
	public int saveUpdate(BlackListVo entity) {
	    //return blackListService.saveUpdate(entity);
        return 0;
	}

	@Override
	public int delete(Long id) {
		return blackListService.delete(id);
	}

	@Override
	public int delete(String id) {
		return blackListService.delete(id);
	}


}