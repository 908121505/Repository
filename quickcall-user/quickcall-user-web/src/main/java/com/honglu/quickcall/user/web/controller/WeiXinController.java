package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.WeiXinRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiangping
 * @date 2018-11-08 21:04
 */
@Controller
public class WeiXinController {

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 获取微信openid
     * @param params
     * @return
     */
    @RequestMapping("/getOpenId")
    @ResponseBody
    public WebResponseModel getOpenId(WeiXinRequest params){
        if (StringUtils.isNotBlank(params.getCode())&& StringUtils.isNotBlank(params.getPhone())) {
            WebResponseModel response = new WebResponseModel();
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg(UserBizReturnCode.paramError.desc());
            return response;
        }
        return userCenterService.execute(params);
    }
}
