package com.honglu.quickcall.user.web.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.api.util.ConstantUtils;
import com.honglu.quickcall.common.api.util.DES3Utils;
import com.honglu.quickcall.user.facade.business.UserDubboBusiness;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.web.service.UserCenterService;


/**
 * Created by len.song on 2017-12-08.
 */
@Service
public class UserCenterServiceImpl implements UserCenterService {
    private static final Logger logger = LoggerFactory.getLogger(UserCenterServiceImpl.class);

    @Autowired
    private UserDubboBusiness userDubboBusiness;

    @Override
    public WebResponseModel execute(AbstractRequest request) {
        request.setService(MyServiceCode.USER);
        request.setSource(SourceCode.OpenApi);
        logger.info("功能编码为"+request.getBizCode()+"发送请求：{}", request);
        WebResponseModel response = new WebResponseModel();
        try{
            CommonResponse $response = userDubboBusiness.excute(request);
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
            response.setCode(UserBizReturnCode.Unknown.code());
            response.setMsg(e.getMessage());
        }
        logger.info("返回结果{}", response);
        return response;
    }

}
