package com.honglu.quickcall.consumer.core.service;

import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.DataBuryFirstChargeReq;
import com.honglu.quickcall.databury.facade.req.databury.DataBuryMakeOrderReq;
import com.honglu.quickcall.databury.facade.req.databury.DataBurySetPwdDurationReq;

import java.util.Map;

/**
 * 数据埋点处理
 * @author xiangping
 * @date 2018-10-30 12:33
 */
public interface DataBuriedPointService {

    /**
     * 获取验证码数据-埋点
     *
     * @param params
     * @return
     * @throws DataBuriedPointException
     */
    void saveGetCodeData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 注册成功数据--埋点
     *
     * @param params
     * @throws DataBuriedPointException
     */
    void saveSignUpResultData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 登陆成功数据--埋点
     *
     * @param params
     * @throws DataBuriedPointException
     */
    void saveUserIdLoginResultData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 接单按钮状态数据--埋点
     *
     * @param params
     * @throws DataBuriedPointException
     */
    void saveOrderButtonData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 提交订单数据-埋点
     *
     * @param params
     * @return
     * @throws DataBuriedPointException
     */
    void saveSubmitOrderData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 下单-数据埋点
     * @param params
     */
    void buryMakeOrderData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 更新密码页面-数据埋点
     * @param params
     */
    void burySetPwdDurationData(Map<String,Object> params) throws DataBuriedPointException;

    /**
     * 首次充值-数据埋点
     * @param params
     */
    void buryFirstChargeData(Map<String,Object> params) throws DataBuriedPointException;
}
