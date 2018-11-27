package com.honglu.quickcall.databury.facade.business;


import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;

/**
 * @author xiangping
 * @date 2018-10-30 23:30
 */
public interface DataBuryBusiness {
    /**
     * 获取验证码数据-埋点
     *
     * @param req
     * @return
     * @throws DataBuriedPointException
     */
    void saveGetCodeData(DataBuryPointGetCodeReq req) throws DataBuriedPointException;

    /**
     * 注册成功数据--埋点
     *
     * @param req
     * @throws DataBuriedPointException
     */
    void saveSignUpResultData(DataBuryPointRegistReq req) throws DataBuriedPointException;

    /**
     * 登陆成功数据--埋点
     *
     * @param req
     * @throws DataBuriedPointException
     */
    void saveUserIdLoginResultData(DataBuryPointLoginReq req) throws DataBuriedPointException;

    /**
     * 接单按钮状态数据--埋点
     *
     * @param req
     * @throws DataBuriedPointException
     */
    void saveOrderButtonData(DataBuryPointOrderButtonReq req) throws DataBuriedPointException;

    /**
     * 提交订单数据-埋点
     *
     * @param req
     * @return
     * @throws DataBuriedPointException
     */
    void saveSubmitOrderData(DataBuryPointSubmitOrderReq req) throws DataBuriedPointException;

    /**
     * 下单-数据埋点
     * @param req
     */
    void buryMakeOrderData(DataBuryMakeOrderReq req) throws DataBuriedPointException;

    /**
     * 更新密码页面-数据埋点
     * @param req
     */
    void burySetPwdDurationData(DataBurySetPwdDurationReq req) throws DataBuriedPointException;

    /**
     * 首次充值-数据埋点
     * @param req
     */
    void buryFirstChargeData(DataBuryFirstChargeReq req) throws DataBuriedPointException;

}
