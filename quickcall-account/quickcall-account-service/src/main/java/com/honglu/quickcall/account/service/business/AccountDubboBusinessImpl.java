package com.honglu.quickcall.account.service.business;

import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.exchange.request.*;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.honglu.quickcall.account.facade.exchange.request.ConsumeRequest;

/**
 * Created by len.song on 2017-12-18.
 */
@Service("Account.AccountDubboBusiness")
public class AccountDubboBusinessImpl implements AccountDubboBusiness {
    private static final Logger logger = LoggerFactory.getLogger(AccountDubboBusinessImpl.class);

   
    @Override
    public CommonResponse excute(AbstractRequest request) {
        logger.info("请求参数为:{}",request);
        if (request == null) {
            throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
        }

        CommonResponse response = new CommonResponse();
        try {
            switch (request.getBizCode()) {
                

                default:

            }
        }catch (BaseException e){
            logger.error("接口编码为："+request.getBizCode()+"异常："+e.getMessage(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }catch (Exception e){
            logger.error("接口编码为："+request.getBizCode()+"异常："+e.getMessage(), e);
            response.setCode(UserBizReturnCode.Unknown);
            response.setMessage(e.getMessage()==null?e+"" :e.getMessage()+e);
        }
        logger.info("返回结果{}", response);
        return response;

    }
}
