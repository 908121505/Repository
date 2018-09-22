package com.honglu.quickcall.user.service.business;


import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.business.UserDubboBusiness;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.service.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("User.UserDubboBusiness")
public class UserDubboBusinessImpl implements UserDubboBusiness {

    private static final Logger logger = LoggerFactory.getLogger(UserDubboBusinessImpl.class);

    @Autowired
    private CommonPersonService commonPersonService;
    @Autowired
    private PersonInfoService personInfoService;
   

    @Override
    public CommonResponse excute(AbstractRequest request) {
        if (request == null) {
            throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
        }
        CommonResponse response = new CommonResponse();
        try {
            switch (request.getBizCode()) {
            case UserFunctionType.PersonInfo://查看个人信息
				response = personInfoService.queryPersonInfo((PersonInfoRequest) request);
				break;    
                default:
                    throw new BizException(UserBizReturnCode.BizFunctionTypeNotMatch, UserBizReturnCode.BizFunctionTypeNotMatch.desc());
            }
        } catch (BaseException e) {
            logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
            response.setCode(UserBizReturnCode.Unknown);
            response.setMessage(e.getMessage() == null ? e + "" : e.getMessage() + e);
        }
        logger.info("返回结果{}", response);
        return response;
    }
}
