package com.calf.module.customerservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.customerservice.vo.FeedBackVO;

@Controller
@RequestMapping("/feedBack")
public class FeedBackController implements BaseController<FeedBackVO> {
	@Autowired
    private BaseManager baseManager;
	
	@Autowired
    private CommonUtilService commonUtilService;
	
    

    private static final String JSP_PATH = "customer_service/feedBack/%s";


	@Override
	public String home() {
		return String.format(JSP_PATH, "feedBackList");
	}


	@SuppressWarnings("unchecked")
	@Override
	public DataTables<FeedBackVO> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sTime = (String) parameters.get("startTime");
        if (StringUtils.isNotBlank(sTime)) {
            sTime = sTime + " 00:00:00";
            parameters.put("startTime", sTime);
        }
        String eTime = (String) parameters.get("endTime");
        if (StringUtils.isNotBlank(eTime)) {
            eTime = eTime + " 23:59:59";
            parameters.put("endTime", eTime);
        }
        String sEcho = (String) parameters.get("sEcho");
        List<FeedBackVO> banners = baseManager.query("FeedBack.queryFeedBackList", parameters);
        int total = baseManager.get("FeedBack.queryCount", parameters);
        return new DataTables<>(sEcho, banners, banners.size(), total);
	}
	
	
	@Override
	public int saveUpdate(FeedBackVO entity) {
		entity.setModifyMan(commonUtilService.getCurrUser());
		entity.setHandleUser(commonUtilService.getCurrUser());
		return baseManager.update("FeedBack.updateEntity",entity);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		FeedBackVO fbvo = baseManager.get("FeedBack.queryFeedBackById", map);
		model.addAttribute("entity", fbvo);
		return String.format(JSP_PATH, "feedBackEdit");
	}


	@Override
	public int saveAdd(FeedBackVO entity) {
		
		return 0;
	}


	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(String id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		return baseManager.delete("FeedBack.deleteEntity", map);
	}
}
