package com.honglu.quickcall.consumer.core.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.consumer.core.service.DataBuriedPointService;
import com.honglu.quickcall.databury.facade.business.DataBuryBusiness;
import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import com.honglu.quickcall.databury.facade.req.databury.UserBean;
import com.honglu.quickcall.producer.facade.req.databury.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiangping
 * @date 2018-10-30 16:26
 */
@Service("dataBuriedPointService")
public class DataBuriedPointServiceImpl implements DataBuriedPointService{
    private static final Logger logger = LoggerFactory.getLogger(DataBuriedPointService.class);

    @Reference(group = "buryDataCenter",version = "1.0.0", timeout = 10000, retries = 0)
    private DataBuryBusiness dataDuryBusiness;

    @Override
    public void saveGetCodeData(Map<String,Object> params) throws DataBuriedPointException {
        String body = (String)params.get("param");
        DataBuriedPointGetCodeReq req = JSON.parseObject(body, DataBuriedPointGetCodeReq.class);
        DataBuryPointGetCodeReq request = new DataBuryPointGetCodeReq();
        request.setPhone(req.getPhone());
        request.setSuccess(req.isSuccess());
        dataDuryBusiness.saveGetCodeData(request);
    }

    @Override
    public void saveSignUpResultData(Map<String,Object> params) throws DataBuriedPointException {
        String body = (String)params.get("param");
        DataBuriedPointRegistReq req = JSON.parseObject(body,DataBuriedPointRegistReq.class);
        DataBuryPointRegistReq request = new DataBuryPointRegistReq();
        request.setPhoneNumber(req.getPhoneNumber());
        request.setRegistDate(req.getRegistDate());
        request.setRegistSource(req.getRegistSource());
        request.setUser_id(req.getUser_id());

        if (req.getUserBean()!=null){
            UserBean userBean = new UserBean();
            userBean.setGender(req.getUserBean().getGender());
            userBean.setNick(req.getUserBean().getNick());
            userBean.setNumberOfCencerns(req.getUserBean().getNumberOfCencerns());
            userBean.setPhoneNumber(req.getUserBean().getPhoneNumber());
            if (req.getUserBean().getRegistDate()!=null) {
                userBean.setRegistDate(DateUtils.formatDate(req.getUserBean().getRegistDate(),"yyyy-MM-dd HH:mm:ss"));
            }
            userBean.setRegistSource(req.getUserBean().getRegistSource());
            userBean.setUserEquipment(req.getUserBean().getUserEquipment());
            userBean.setUserIdentity(req.getUserBean().getUserIdentity());
            userBean.setVc_user_id(req.getUser_id());
            userBean.setVermicelli(req.getUserBean().getVermicelli());

            if (req.getUserBean().getYearOfBirth()!=null) {
                userBean.setYearOfBirth(DateUtils.formatDate(req.getUserBean().getYearOfBirth(),"yyyy-MM-dd HH:mm:ss"));
            }
            request.setUserBean(userBean);
        }

        dataDuryBusiness.saveSignUpResultData(request);
    }

    @Override
    public void saveUserIdLoginResultData(Map<String,Object> params) throws DataBuriedPointException {
        String body = (String)params.get("param");
        DataBuriedPointLoginReq req = JSON.parseObject(body,DataBuriedPointLoginReq.class);
        DataBuryPointLoginReq request = new DataBuryPointLoginReq();
        request.setLoginmethod(req.getLoginmethod());
        request.setPhoneNumber(req.getPhoneNumber());
        request.setUser_id(req.getUser_id());

        if (req.getUserBean()!=null){
            UserBean userBean = new UserBean();
            userBean.setGender(req.getUserBean().getGender());
            userBean.setNick(req.getUserBean().getNick());
            userBean.setNumberOfCencerns(req.getUserBean().getNumberOfCencerns());
            userBean.setPhoneNumber(req.getUserBean().getPhoneNumber());
            if (req.getUserBean().getRegistDate()!=null) {
                userBean.setRegistDate(DateUtils.formatDate(req.getUserBean().getRegistDate(),"yyyy-MM-dd HH:mm:ss"));
            }
            userBean.setRegistSource(req.getUserBean().getRegistSource());
            userBean.setUserEquipment(req.getUserBean().getUserEquipment());
            userBean.setUserIdentity(req.getUserBean().getUserIdentity());
            userBean.setVc_user_id(req.getUser_id());
            userBean.setVermicelli(req.getUserBean().getVermicelli());

            if (req.getUserBean().getYearOfBirth()!=null) {
                userBean.setYearOfBirth(DateUtils.formatDate(req.getUserBean().getYearOfBirth(),"yyyy-MM-dd HH:mm:ss"));
            }
            request.setUserBean(userBean);
        }

        dataDuryBusiness.saveUserIdLoginResultData(request);
    }

    @Override
    public void saveOrderButtonData(Map<String,Object> params) throws DataBuriedPointException {
        String body = (String)params.get("param");
        DataBuriedPointOrderButtonReq req = JSON.parseObject(body,DataBuriedPointOrderButtonReq.class);
        DataBuryPointOrderButtonReq request = new DataBuryPointOrderButtonReq();
        request.setButtonexecution_time(req.getButtonexecution_time());
        request.setOrderbutton_status(req.getOrderbutton_status());
        request.setUser_id(req.getUser_id());
        dataDuryBusiness.saveOrderButtonData(request);
    }

    @Override
    public void saveSubmitOrderData(Map<String,Object> params) throws DataBuriedPointException {
        String body = (String)params.get("param");
        DataBuriedPointSubmitOrderReq req = JSON.parseObject(body,DataBuriedPointSubmitOrderReq.class);
        DataBuryPointSubmitOrderReq request = new DataBuryPointSubmitOrderReq();
        request.setActual_payment_amount(req.getActual_payment_amount());
        request.setOrder_amount(req.getOrder_amount());
        request.setOrder_id(req.getOrder_id());
        request.setOrder_quantity(req.getOrder_quantity());
        request.setOrder_type(req.getOrder_type());
        request.setUser_id(req.getUser_id());
        dataDuryBusiness.saveSubmitOrderData(request);
    }
}
