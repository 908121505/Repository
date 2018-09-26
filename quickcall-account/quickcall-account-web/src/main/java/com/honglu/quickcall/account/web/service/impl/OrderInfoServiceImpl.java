package com.honglu.quickcall.account.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.web.service.IOrderInfoService;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能管理中心
 * @Package: com.honglu.quickcall.account.web.service.impl 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:17:04
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

//    @Autowired
//    private OrderInfoBussiness orderInfoBussiness;
    @Autowired
    private AccountDubboBusiness accountDubboBusiness;


    @Override
    public WebResponseModel execute(AbstractRequest request) {
        request.setService(MyServiceCode.ACCOUNT);
        request.setSource(SourceCode.OpenApi);
        LOGGER.info("功能编码为" + request.getBizCode() + "发送请求：{}", request);
        WebResponseModel response = new WebResponseModel();
        try {
            CommonResponse cResponse = accountDubboBusiness.excute(request);
//            CommonResponse cResponse = orderInfoBussiness.excute(request);
            LOGGER.info("功能编码为" + request.getBizCode() + "接收响应：{}", cResponse);
            if (!cResponse.isSuccess()) {
                throw new RemoteException(cResponse.getCode(), cResponse.getMessage());
            }
            response.setCode(cResponse.getCode().code());
            response.setMsg(cResponse.getMessage());
            response.setData(JSON.toJSONString(cResponse.getData()));
        }catch (RemoteException e){
            LOGGER.error("功能编码为"+request.getBizCode()+"的远程调用异常"+e.getMessage(), e);
            response.setCode(e.getCode().code());
            response.setMsg(e.getMessage());
            response.setData("");
        }catch (Exception e){
            LOGGER.error("功能编码为"+request.getBizCode()+"接口未知异常"+e.getMessage(), e);
            response.setCode(AccountBizReturnCode.Unknown.code());
            response.setMsg(e.getMessage());
        }
        LOGGER.info("返回结果{}", response);
        return response;
    }
}
