package com.honglu.quickcall.account.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.web.service.AccountCenterService;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountCenterServiceImpl implements AccountCenterService {

    private static final Logger logger = LoggerFactory.getLogger(AccountCenterServiceImpl.class);

    @Autowired
    private AccountDubboBusiness accountDubboBusiness;

    @Override
    public WebResponseModel execute(AbstractRequest request) {
        request.setService(MyServiceCode.ACCOUNT);
        request.setSource(SourceCode.OpenApi);
        logger.info("功能编码为"+request.getBizCode()+"发送请求：{}", request);
        WebResponseModel response = new WebResponseModel();
        try{
            CommonResponse $response = accountDubboBusiness.excute(request);
            logger.info("功能编码为"+request.getBizCode()+"接收响应：{}", $response);
            if(!$response.isSuccess()){
                throw new RemoteException($response.getCode(), $response.getMessage());
            }

            response.setCode($response.getCode().code());
            response.setMsg($response.getMessage());
//            response.setData(DES3Utils.encryptMode(JSON.toJSONString($response.getData()), ConstantUtils.THREEDES_KEY));
            response.setData(JSON.toJSONString($response.getData()));
        }catch (RemoteException e){
            logger.error("功能编码为"+request.getBizCode()+"的远程调用异常"+e.getMessage(), e);
            response.setCode(e.getCode().code());
            response.setMsg(e.getMessage());
            response.setData("");
        }catch (Exception e){
            logger.error("功能编码为"+request.getBizCode()+"接口未知异常"+e.getMessage(), e);
            response.setCode(AccountBizReturnCode.Unknown.code());
            response.setMsg(e.getMessage());
        }
        logger.info("返回结果{}", response);
        return response;
    }
}
