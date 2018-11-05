package com.honglu.quickcall.databury.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.databury.core.enums.EventEnums;
import com.honglu.quickcall.databury.core.service.DataBuriedPointService;
import com.honglu.quickcall.databury.core.utils.BuryiedPointDataConvertor;
import com.honglu.quickcall.databury.core.utils.BuryiedPointUtil;
import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BuryiedPointUtil buryiedPointUtil;

    @Override
    public void saveGetCodeData(DataBuryPointGetCodeReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】获取验证码数据埋点开始--参数为:{}", req);
        if(req==null){
            throw new DataBuriedPointException("神策埋点-获取验证码数据--消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("phone",req.getPhone());
        if (req.isSuccess()==true){
            params.put("isSuccess","true");
        }else{
            params.put("isSuccess","false");
        }

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_getCode.getValue(),(String)params.get("phone"),params);

        logger.info("----【神策埋点】获取验证码数据准备埋点--数据={}", JSON.toJSONString(event));
        buryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】获取验证码数据埋点结束");
    }

    @Override
    public void saveSignUpResultData(DataBuryPointRegistReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】注册成功数据埋点开始--参数为:{}",req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-注册成功数据-参数的mq参数为空");
        }
        Map<String,Object> event = new HashMap<>();
        event.put("vc_user_id",req.getUser_id());
        event.put("phoneNumber",req.getPhoneNumber());
        if (req.getRegistDate()!=null){
            event.put("registDate", DateUtils.formatDate(req.getRegistDate(),"yyyy-MM-dd HH:mm:ss"));
        }else{
            event.put("registDate",req.getRegistDate());
        }
        event.put("registSource",req.getRegistSource());

        logger.info("===============datbury注册埋点用户数据："+req.getUserBean());

        Map<String, Object> user = getUserMap(req.getUserBean(),req.getPhoneNumber());

        Map<String,Object> data = BuryiedPointDataConvertor.newInstanceAll(EventEnums.EVENT_Sign_up_result.getValue(),req.getUser_id(),event,user,req.getVirUserId());

        logger.info("----【神策埋点】注册成功数据准备埋点--数据={}", JSON.toJSONString(data));
        buryiedPointUtil.buryData(data);
        logger.info("----【神策埋点】注册成功数据埋点结束");
    }

    private Map<String,Object> getUserMap(UserBean userBean,String phoneNumber){
        Map<String, Object> user = new HashMap<>(16);
        if (userBean!=null) {
            user.put("gender", userBean.getGender());
            user.put("phoneNumber", phoneNumber);
            user.put("yearOfBirth", userBean.getYearOfBirth());
            user.put("vc_user_id", userBean.getVc_user_id());
            user.put("registSource", userBean.getRegistSource());
            user.put("registDate", userBean.getRegistDate());
            user.put("nickname", userBean.getNick());
            user.put("Vermicelli", userBean.getVermicelli());
            user.put("Number of cencerns", userBean.getNumberOfCencerns());
            user.put("User identity", userBean.getUserIdentity());
            user.put("User equipment", userBean.getUserEquipment());
        }
        return user;
    }

    @Override
    public void saveUserIdLoginResultData(DataBuryPointLoginReq req) throws DataBuriedPointException {
        logger.info("====【神策埋点】登陆成功数据埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-登陆成功数据-参数的mq参数为空");
        }

        Map<String,Object> event = new HashMap<>();
        event.put("loginMethod",req.getLoginmethod());
        event.put("vc_user_id",req.getUser_id());
        event.put("phoneNumber",req.getPhoneNumber());

        logger.info("===============datbury登陆埋点用户数据："+req.getUserBean());

        Map<String, Object> user = getUserMap(req.getUserBean(),req.getPhoneNumber());

        Map<String,Object> map = BuryiedPointDataConvertor.newInstanceAll(EventEnums.EVENT_User_id_login_result.getValue(),req.getUser_id(),event,user,req.getVirUserId());

        logger.info("----【神策埋点】登陆成功数据准备埋点--数据={}", JSON.toJSONString(map));
        buryiedPointUtil.buryData(map);
        logger.info("----【神策埋点】登陆成功数据埋点结束");
    }

    @Override
    public void saveOrderButtonData(DataBuryPointOrderButtonReq req) throws DataBuriedPointException {
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
        buryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】接单按钮状态数据埋点结束");
    }

    @Override
    public void saveSubmitOrderData(DataBuryPointSubmitOrderReq req) throws DataBuriedPointException {
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
        buryiedPointUtil.buryData(event);
        logger.info("----【神策埋点】提交订单数据埋点结束");
    }
}
