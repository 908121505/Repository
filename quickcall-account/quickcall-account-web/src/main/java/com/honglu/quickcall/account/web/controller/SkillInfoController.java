package com.honglu.quickcall.account.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.facade.exchange.request.CheckReceiveSwitchRequest;
import com.honglu.quickcall.account.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageDaVinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageSkillinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.OpenReceiveSwitchRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.web.service.IOrderInfoService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

@Controller
@RequestMapping("/skill")
public class SkillInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 大V接单设置展示
     * @param params
     * @return
     */
    @RequestMapping(value = "/getSkillInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel querySkillInfoPersonal(SkillInfoRequest params) {
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
    public WebResponseModel updateSkillInfoPersonal(@RequestBody   SkillUpdateRequest params) {
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
    public WebResponseModel getFirstPageDaVinfo(FirstPageDaVinfoRequest params) {
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
    
    

    
    
    /**
     * 检查接单开关
     * @param params
     * @return
     */
    @RequestMapping(value = "/getDaVListByType", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getDaVListBySkillId(DaVListBySkillItemIdRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    /**
     * 检查声优接单开关是否开启
     * @param params
     * @return
     */
    @RequestMapping(value = "/checkReceiveSwitch", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel checkReceiveSwitch(CheckReceiveSwitchRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    /**
     * 开启声优接单开关
     * @param params
     * @return
     */
    @RequestMapping(value = "/openReceiveSwitch", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel openReceiveSwitch(OpenReceiveSwitchRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    
    
    
    
}
