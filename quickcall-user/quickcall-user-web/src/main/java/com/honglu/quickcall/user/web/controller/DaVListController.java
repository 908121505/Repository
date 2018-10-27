package com.honglu.quickcall.user.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;

@Controller
@RequestMapping("/daVList")
public class DaVListController {
	 private static Logger LOGGER = LoggerFactory.getLogger(CertificationController.class);
	 
    @Autowired
    private UserCenterService userCenterService;
    /**
     * 根据技能ID获取该分类下的大V列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/getDaVListByType", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getDaVListBySkillId(DaVListBySkillItemIdRequest params) {
    	WebResponseModel response = userCenterService.execute(params);
    	return response;
    }
}
