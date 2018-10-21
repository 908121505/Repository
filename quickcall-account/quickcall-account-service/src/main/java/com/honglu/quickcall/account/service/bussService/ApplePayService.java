package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.ApplePayRequest;
import com.honglu.quickcall.account.facade.exchange.request.ApplePurchaseRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * @author: xiangxianjin
 * @date: 2018/10/20 15:16
 * @description: 苹果支付服务
 */
public interface ApplePayService {

    /**
     * 创建苹果预支付订单
     * @param applePayRequest
     * @return
     */
    CommonResponse createOrder(ApplePayRequest applePayRequest);

    /**
     * 苹果内购回调验证
     * @param applePurchaseRequest
     * @return
     */
    CommonResponse applePurchase(ApplePurchaseRequest applePurchaseRequest);

}
