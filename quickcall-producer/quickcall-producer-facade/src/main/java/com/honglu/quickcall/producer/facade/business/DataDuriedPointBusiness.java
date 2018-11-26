package com.honglu.quickcall.producer.facade.business;

import com.honglu.quickcall.producer.facade.req.databury.*;

/**
 * 数据埋点接口
 *
 * @author xiangping
 * @date 2018-10-30 16:13
 */
public interface DataDuriedPointBusiness {

    /**
     * 手机验证码-数据埋点
     * @param req
     */
    void buryGetCodeData(DataBuriedPointGetCodeReq req);

    /**
     * 注册成功-数据埋点
     * @param req
     */
    void burySignUpResultData(DataBuriedPointRegistReq req);

    /**
     * 登陆成功-数据埋点
     * @param req
     */
    void buryUserIdLoginResultData(DataBuriedPointLoginReq req);

    /**
     * 接单按钮-数据埋点
     * @param req
     */
    void buryOrderButtonData(DataBuriedPointOrderButtonReq req);

    /**
     * 提交订单-数据埋点
     * @param req
     */
    void burySubmitOrderData(DataBuriedPointSubmitOrderReq req);

    /**
     * 下单-数据埋点
     * @param req
     */
    void buryMakeOrderData(BuryMakeOrderReq req);

    /**
     * 更新密码页面-数据埋点
     * @param req
     */
    void burySetPwdDurationData(BurySetPwdDurationReq req);

    /**
     * 首次充值-数据埋点
     * @param req
     */
    void buryFirstChargeData(BuryFirstChargeReq req);

}
