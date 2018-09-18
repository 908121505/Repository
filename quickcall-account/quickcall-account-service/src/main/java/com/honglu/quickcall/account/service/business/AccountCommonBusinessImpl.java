package com.honglu.quickcall.account.service.business;

import com.honglu.quickcall.account.facade.business.AccountCommonBusiness;
import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.GetRechargeAmountRequest;
import com.honglu.quickcall.account.facade.exchange.request.GetRechargeByAmountRequest;
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

/**
 * Created by len.song on 2018-03-06.
 */
@Service("Account.AccountCommonBusiness")
public class AccountCommonBusinessImpl implements AccountCommonBusiness {
    private static final Logger logger = LoggerFactory.getLogger(AccountCommonBusinessImpl.class);


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
