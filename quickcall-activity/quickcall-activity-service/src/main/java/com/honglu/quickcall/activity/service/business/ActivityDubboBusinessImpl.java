package com.honglu.quickcall.activity.service.business;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.business.ActivityDubboBusiness;
import com.honglu.quickcall.activity.facade.code.ActivityBizReturnCode;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;


@Service("Activity.ActivityDubboBusiness")
public class ActivityDubboBusinessImpl implements ActivityDubboBusiness {

    private static final Logger logger = LoggerFactory.getLogger(ActivityDubboBusinessImpl.class);

   

    @Override
    public CommonResponse excute(AbstractRequest request) {
        if(request == null){
            throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
        }
        CommonResponse response = new CommonResponse();
        try{

            switch (request.getBizCode()) {
               
               /* case ActivityFunctionType.welcomeGodOfWealth:
                	response=activityService.welcomeGodOfWealth((WelcomeGodOfWealthRequest)request);
                	break;*/
                default:
                    throw new BizException(ActivityBizReturnCode.BizFunctionTypeNotMatch,ActivityBizReturnCode.BizFunctionTypeNotMatch.desc());
            }

        }catch (BaseException e){
            logger.error("接口编码为："+request.getBizCode()+"异常："+e.getMessage(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }catch (Exception e){
            logger.error("接口编码为："+request.getBizCode()+"异常："+e.getMessage(), e);
            response.setCode(ActivityBizReturnCode.Unknown);
            response.setMessage(e.getMessage());
        }
        logger.info("返回结果{}", response);
        return response;
    }
}
