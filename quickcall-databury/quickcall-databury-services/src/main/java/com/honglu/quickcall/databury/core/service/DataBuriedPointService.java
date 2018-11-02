package com.honglu.quickcall.databury.core.service;


import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import com.honglu.quickcall.databury.facade.resp.databury.*;

/**
 * @author xiangping
 * @date 2018-10-30 16:51
 */
public interface DataBuriedPointService {

    /**
     * 获取验证码数据-埋点
     *
     * @param req
     * @return
     * @throws DataBuriedPointException
     */
    DataBuryPointGetCodeResp saveGetCodeData(DataBuryPointGetCodeReq req) throws DataBuriedPointException;

    /**
     * 注册成功数据--埋点
     *
     * @param req
     * @throws DataBuriedPointException
     */
    DataBuryPointRegistResp saveSignUpResultData(DataBuryPointRegistReq req) throws DataBuriedPointException;

    /**
     * 登陆成功数据--埋点
     *
     * @param req
     * @throws DataBuriedPointException
     */
    DataBuryPointLoginResp saveUserIdLoginResultData(DataBuryPointLoginReq req) throws DataBuriedPointException;

    /**
     * 接单按钮状态数据--埋点
     *
     * @param req
     * @throws DataBuriedPointException
     */
    DataBuryPointOrderButtonResp saveOrderButtonData(DataBuryPointOrderButtonReq req) throws DataBuriedPointException;

    /**
     * 提交订单数据-埋点
     *
     * @param req
     * @return
     * @throws DataBuriedPointException
     */
    DataBuryPointSubmitOrderResp saveSubmitOrderData(DataBuryPointSubmitOrderReq req) throws DataBuriedPointException;

}
