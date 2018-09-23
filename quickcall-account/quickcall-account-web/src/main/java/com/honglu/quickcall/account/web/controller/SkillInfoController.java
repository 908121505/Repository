package com.honglu.quickcall.account.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.facade.exchange.request.FirstPageDaVinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageSkillinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.web.service.OrderInfoService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

@Controller
@RequestMapping("/skill")
public class SkillInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 大V接单设置展示
     * @param params
     * @return
     */
    @RequestMapping(value = "/getSkillInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel querySkillInfoPersonal( SkillInfoRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
        return response;
    }
    
   /**
    * 大V接单设置（开启关闭修改）
    * @param params
    * @return
    */
    @RequestMapping(value = "/updateSkillInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel updateSkillInfoPersonal( SkillUpdateRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    
    /**
     * 首页大V技能展示
     * @param params
     * @return
     */
    @RequestMapping(value = "/getFirstPageDaVinfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getFirstPageDaVinfo( FirstPageDaVinfoRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 首页技能种类展示
     * @param params
     * @return
     */
    @RequestMapping(value = "/getFirstPageSkillinfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getFirstPageSkillinfo(FirstPageSkillinfoRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    
    
    
    
}
