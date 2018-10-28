package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageQueryRequest;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageSaveRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;
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
 * 用户消息Controller
 *
 * @author duanjun
 * @date 2018-09-22 17:02
 */
@Controller
@RequestMapping("/userMessage")
public class UserMessageController {
    private final static Logger logger = LoggerFactory.getLogger(UserCommonController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 查询未读消息数量
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/unreadMessageNum", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel unreadMessageNum(UserUnreadMessageNumRequest params) {
        if (params.getCustomerId() == null) {
            WebResponseModel response = new WebResponseModel();
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg(UserBizReturnCode.paramError.desc());
            return response;
        }
        return userCenterService.execute(params);
    }

    /**
     * 创建预约消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveBookingMessage", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveBookingMessage(BookingMessageSaveRequest request) {
        if (StringUtils.isBlank(String.valueOf(request.getCustomerId()))||
            StringUtils.isBlank(String.valueOf(request.getReceiverId()))||
            StringUtils.isBlank(request.getTitle())||
            StringUtils.isBlank(request.getPriceUnitTimeCount())||
            StringUtils.isBlank(request.getPriceUnit())) {
            WebResponseModel response = new WebResponseModel();
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg(UserBizReturnCode.paramError.desc());
            return response;
        }
        return userCenterService.execute(request);
    }

    /**
     * 获取预约消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBookingMessage", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getBookingMessage(BookingMessageQueryRequest request) {
        if (StringUtils.isBlank(String.valueOf(request.getReceiverId()))) {
            WebResponseModel response = new WebResponseModel();
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg(UserBizReturnCode.paramError.desc());
            return response;
        }
        return userCenterService.execute(request);
    }
}
