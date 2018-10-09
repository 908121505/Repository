package com.honglu.quickcall.user.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryAttentionFansListRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryInterestListRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryOccupationListRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveBirthRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveGenderRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveInterestRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveNickNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveOccupationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveSignNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.ShowHomePageLogout;
import com.honglu.quickcall.user.web.service.UserCenterService;

/**
 * @author liuyinkai
 */
@Controller
@RequestMapping("/account/info")
public class AccountInfoController {

    @Autowired
    private  UserCenterService userCenterService;

    /**
     *查询个人信息
     */
    @RequestMapping(value = "/queryPersonInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryPersonInfo(PersonInfoRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     *保存昵称和头像
     */
    @RequestMapping(value = "/saveNicknameImage", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveNicknameImage(SaveNickNameRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 保存性别
     */
    @RequestMapping(value = "/saveGender", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveGender(SaveGenderRequest params) {
    	 WebResponseModel response = userCenterService.execute(params);
         return response;
    }
    /**
     *保存签名
     */
    @RequestMapping(value = "/saveSignName", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveSignName(SaveSignNameRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 保存生日
     */
    @RequestMapping(value = "/saveBirthday", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveBirthday(SaveBirthRequest params) {
   	 WebResponseModel response = userCenterService.execute(params);
        return response;
   }
    /**
     * 保存兴趣爱好
     */
    @RequestMapping(value = "/saveInterest", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveInterest(SaveInterestRequest params) {
   	 WebResponseModel response = userCenterService.execute(params);
        return response;
   }
    /**
     * 查询兴趣列表
     */
    @RequestMapping(value = "/queryInterestList", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryInterestList(QueryInterestListRequest params) {
    	WebResponseModel response = userCenterService.execute(params);
    	return response;
    }
    /**
     * 保存职业
     */
    @RequestMapping(value = "/saveOccupation", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveOccupation(SaveOccupationRequest params) {
   	 WebResponseModel response = userCenterService.execute(params);
        return response;
   } 
    /**
     * 查询职业列表
     */
    @RequestMapping(value = "/queryOccupationList", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryOccupationList(QueryOccupationListRequest params) {
    	WebResponseModel response = userCenterService.execute(params);
    	return response;
    } 
    /**
     * 大V主页，普通用户主页（客态）
     */
    @RequestMapping(value = "/showHomePageLogout", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel showHomePageLogout(ShowHomePageLogout params) {
   	 WebResponseModel response = userCenterService.execute(params);
        return response;
   } 
    


    /**
     * 查询关注列表
     */
    @RequestMapping(value = "/queryAttentionFansList", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryAttentionFansList(QueryAttentionFansListRequest params) {
    	WebResponseModel response = userCenterService.execute(params);
    	return response;
    }
    
    
    
    
    
}
