package com.honglu.quickcall.databury.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.databury.core.enums.EventEnums;
import com.honglu.quickcall.databury.core.service.DataBuriedPointService;
import com.honglu.quickcall.databury.core.utils.BuryiedPointDataConvertor;
import com.honglu.quickcall.databury.core.utils.BuryiedPointUtil;
import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import com.honglu.quickcall.databury.facade.resp.databury.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 数据埋点处理
 * @author xiangping
 * @date 2018-10-28 22:33
 */
@Service("dataBuriedPointService")
public class DataBuriedPointServiceImpl implements DataBuriedPointService {

    private static final Logger logger = LoggerFactory.getLogger(DataBuriedPointServiceImpl.class);

    @Override
    public DataBuryPointGetCodeResp saveGetCodeData(DataBuryPointGetCodeReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】获取验证码数据埋点开始--参数为:{}", req);
        if(req==null){
            throw new DataBuriedPointException("神策埋点-获取验证码数据--消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("phone",req.getPhone());
        params.put("isSuccess",req.isSuccess());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_getCode.getValue(),(String)params.get("phone"),params);

        logger.info("----【神策埋点】获取验证码数据准备埋点--数据={}", JSON.toJSONString(event));
        BuryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】获取验证码数据埋点结束");
        return null;
    }

    @Override
    public DataBuryPointRegistResp saveSignUpResultData(DataBuryPointRegistReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】注册成功数据埋点开始--参数为:{}",req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-注册成功数据-参数的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("vc_user_id",req.getUser_id());
        params.put("phoneNumber",req.getPhoneNumber());
        params.put("registDate",req.getRegistDate());
        params.put("registSource",req.getRegistSource());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_Sign_up_result.getValue(),(String)params.get("vc_user_id"),params);

        logger.info("----【神策埋点】注册成功数据准备埋点--数据={}", JSON.toJSONString(event));
        BuryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】注册成功数据埋点结束");

        return null;
    }

    @Override
    public DataBuryPointLoginResp saveUserIdLoginResultData(DataBuryPointLoginReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】登陆成功数据埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-登陆成功数据-参数的mq参数为空");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("loginmethod",req.getLoginmethod());
        params.put("vc_user_id",req.getUser_id());
        params.put("phoneNumber",req.getPhoneNumber());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_User_id_login_result.getValue(),(String)params.get("vc_user_id"),params);

        logger.info("----【神策埋点】登陆成功数据准备埋点--数据={}", JSON.toJSONString(event));
        BuryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】登陆成功数据埋点结束");
        return null;
    }

    @Override
    public DataBuryPointOrderButtonResp saveOrderButtonData(DataBuryPointOrderButtonReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】接单按钮状态数据埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-接单按钮状态数据-参数的mq参数为空");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("orderbutton_status",req.getOrderbutton_status());
        params.put("vc_user_id",req.getUser_id());
        params.put("buttonexecution_time",req.getButtonexecution_time());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_Order_button.getValue(),(String)params.get("vc_user_id"),params);

        logger.info("----【神策埋点】接单按钮状态数据准备埋点--数据={}", JSON.toJSONString(event));
        BuryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】接单按钮状态数据埋点结束");
        return null;
    }

    @Override
    public DataBuryPointSubmitOrderResp saveSubmitOrderData(DataBuryPointSubmitOrderReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】提交订单数据埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-提交订单数据-消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("order_id",req.getOrder_id());
        params.put("order_amount",req.getOrder_amount());
        params.put("vc_user_id",req.getUser_id());
        params.put("order_type",req.getOrder_type());
        params.put("order_quantity",req.getOrder_quantity());
        params.put("actual_payment_amount",req.getActual_payment_amount());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_submitorder.getValue(),(String)params.get("vc_user_id"),params);

        logger.info("----【神策埋点】提交订单数据准备埋点--数据={}", JSON.toJSONString(event));
        BuryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】提交订单数据埋点结束");
        return null;
    }
}
