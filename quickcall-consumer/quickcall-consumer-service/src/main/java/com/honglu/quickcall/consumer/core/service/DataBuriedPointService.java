package com.honglu.quickcall.consumer.core.service;

import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;

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
}
