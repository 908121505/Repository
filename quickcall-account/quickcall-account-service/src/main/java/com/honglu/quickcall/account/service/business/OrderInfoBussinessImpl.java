package com.honglu.quickcall.account.service.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.OrderInfoBussiness;
import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.service.service.SkillService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：主播技能设置
 * @Package: com.honglu.quickcall.account.service.business 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:37:13
 */
@Service("skillBusiness")
public class OrderInfoBussinessImpl implements OrderInfoBussiness {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInfoBussinessImpl.class);
    
    private SkillService  skillService;


    @Override
    public CommonResponse excute(AbstractRequest request) {
        LOGGER.info("请求参数为:{}",request);
        if (request == null) {
            throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
        }

        CommonResponse response = new CommonResponse();

        try {
            switch (request.getBizCode()) {
            case OrderRequestType.QUERY_SKILL_INFO:
				response = skillService.querySkillInfoPersonal((SkillInfoRequest)request);
				break;  
            case OrderRequestType.UPDATE_SKILL_INFO:
                response=  skillService.updateSkillInfoPersonal((SkillUpdateRequest)request);
                break;
                
                
                default:
                    throw new BizException(UserBizReturnCode.BizFunctionTypeNotMatch, UserBizReturnCode.BizFunctionTypeNotMatch.desc());
            }
        }catch (BaseException e){
            LOGGER.error("接口编码为："+request.getBizCode()+"异常："+e.getMessage(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }catch (Exception e){
            LOGGER.error("接口编码为："+request.getBizCode()+"异常："+e.getMessage(), e);
            response.setCode(UserBizReturnCode.Unknown);
            response.setMessage(e.getMessage()==null?e+"" :e.getMessage()+e);
        }
        LOGGER.info("返回结果{}", response);

        return response;

    }
}
