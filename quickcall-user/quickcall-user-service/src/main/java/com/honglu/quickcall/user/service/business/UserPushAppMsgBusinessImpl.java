package com.honglu.quickcall.user.service.business;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.business.UserPushAppMsgBusiness;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.service.service.PushAppMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户中心 -- 推送APP端消息Dubbo服务实现类
 *
 * @author duanjun
 * @date 2018-09-23 17:21
 */
@Service("User.UserPushAppMsgBusiness")
public class UserPushAppMsgBusinessImpl implements UserPushAppMsgBusiness {
    private static final Logger logger = LoggerFactory.getLogger(UserPushAppMsgBusinessImpl.class);

    @Autowired
    private PushAppMsgService pushAppMsgService;

    @Override
    public CommonResponse excute(AbstractRequest request) {
        if (request == null) {
            throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
        }
        CommonResponse response = new CommonResponse();
        try {
            switch (request.getBizCode()) {
                case UserFunctionType.PUSH_APP_MSG:
                    response=  pushAppMsgService.pushMsg((PushAppMsgRequest)request);
                    break;
                case UserFunctionType.PUSH_APP_MSG_JOB:
                    response=  pushAppMsgService.pushMsgJob((PushAppMsgJobRequest)request);
                    break;
                default:
                    throw new BizException(UserBizReturnCode.BizFunctionTypeNotMatch, UserBizReturnCode.BizFunctionTypeNotMatch.desc());
            }
        } catch (BaseException e) {
            logger.error("用户推送消息接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("用户推送消息接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
            response.setCode(UserBizReturnCode.Unknown);
            response.setMessage(e.getMessage() == null ? e + "" : e.getMessage() + e);
        }
        logger.info("用户推送消息返回结果{}", response);
        return response;
    }

}
