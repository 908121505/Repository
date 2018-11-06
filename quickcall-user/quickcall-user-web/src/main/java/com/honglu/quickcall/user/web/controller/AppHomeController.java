package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.FirstPageBigvListRequest;
import com.honglu.quickcall.user.facade.exchange.request.InitBigvScoreRankDataRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * APP应用首页Controller
 *
 * @author duanjun
 * @date 2018-10-25 14:04
 */
@Controller
@RequestMapping("/appHome")
public class AppHomeController {

    private static Logger LOGGER = LoggerFactory.getLogger(CertificationController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 首页大V技能展示
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/getFirstPageDaVinfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getFirstPageDaVinfo(FirstPageBigvListRequest params) {
        LOGGER.info("userWeb.AppHomeController getFirstPageDaVinfo request data : " + params);
        WebResponseModel response = userCenterService.execute(params);
        LOGGER.info("userWeb.AppHomeController getFirstPageDaVinfo response data : " + response);
        return response;
    }

    /**
     * 初始化大V评分排名数据
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/initBigvScoreRankData", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel initBigvScoreRankData(InitBigvScoreRankDataRequest params) {
        LOGGER.info("userWeb.AppHomeController initBigvScoreRankData request data : " + params);
        WebResponseModel response = new WebResponseModel();
        if(!"72739a1ac8374447ad04f56f2f265246".equals(params.getPassword())){
            response.setCode(UserBizReturnCode.CheckSignError.code());
            response.setMsg("密码错误");
            return response;
        }
        response = userCenterService.execute(params);
        LOGGER.info("userWeb.AppHomeController initBigvScoreRankData response data : " + response);
        return response;
    }

}
