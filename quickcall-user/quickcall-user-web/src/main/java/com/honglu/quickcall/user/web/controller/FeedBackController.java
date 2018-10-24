package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.FeedBackInsertRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.util.Strings;

import java.util.regex.Pattern;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
    private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    @Autowired
    UserCenterService userCenterService;

    @RequestMapping("/insertFeedBack")
    @ResponseBody
    public WebResponseModel insertFeedBack(FeedBackInsertRequest feedBackInsertRequest) {
        logger.info("enter insertFeedBack controller {}", feedBackInsertRequest);
        String customerId = feedBackInsertRequest.getCustomerId();
        String feedBackContent = feedBackInsertRequest.getFeedBackContent();
        String contactWay = feedBackInsertRequest.getContactWay();
        WebResponseModel responseModel = new WebResponseModel();
        if (Strings.isNullOrEmpty(customerId)) {
            responseModel.setCode(UserBizReturnCode.paramError.code());
            responseModel.setMsg(UserBizReturnCode.paramError.desc());
            responseModel.setData("反馈人Id不能为空");
            return responseModel;
        }

        if (feedBackContent != null && feedBackContent.length() > 200) {
            responseModel.setCode(UserBizReturnCode.paramError.code());
            responseModel.setMsg(UserBizReturnCode.paramError.desc());
            responseModel.setData("内容长度不能大于200");
            return responseModel;
        }

        if (Strings.isNotNullAndNotEmpty(contactWay) && !Pattern.matches(REGEX_MOBILE, contactWay)) {
            responseModel.setCode(UserBizReturnCode.paramError.code());
            responseModel.setMsg(UserBizReturnCode.paramError.desc());
            responseModel.setData("手机号码格式不对");
            return responseModel;
        }

        return userCenterService.execute(feedBackInsertRequest);
    }

}
