package com.honglu.quickcall.account.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.facade.exchange.request.ApplayRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.ConfirmOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvConfirmRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvReceiveOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvStartServiceRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderDaVProductRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderReceiveOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSaveRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSendOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.PayOrderRequest;
import com.honglu.quickcall.account.web.service.IOrderInfoService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 获取主播开启产品
     * @param params
     * @return
     */
    @RequestMapping(value = "/dvProduct", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryDaVProduct( @RequestBody OrderDaVProductRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
        return response;
    }
    
    /**
     * 用户下单
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveOrder( @RequestBody OrderSaveRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    /**
     * 收到的订单
     * @param params
     * @return
     */
    @RequestMapping(value = "/receiveOrderList", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryReceiveOrderList( @RequestBody OrderReceiveOrderListRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    
    /**
     * 收到的订单
     * @param params
     * @return
     */
    @RequestMapping(value = "/sendOrderList", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel querySendOrderList( @RequestBody OrderSendOrderListRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    /////////////////////////////////////////////////////////////////
    /**
     * 发起的订单页--去支付
     * @param params
     * @return
     */
    @RequestMapping(value = "/detailOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel detailOrder( @RequestBody DetailOrderRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 发起的订单页--去支付
     * @param params
     * @return
     */
    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel payOrder( @RequestBody PayOrderRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 发起的订单页--申请退款/完成
     * @param params
     * @return
     */
    @RequestMapping(value = "/applayRefund", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel applayRefund( @RequestBody ApplayRefundRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 发起的订单页--同意/拒绝
     * @param params
     * @return
     */
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel confirmOrder( @RequestBody ConfirmOrderRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /////////////////////////////////////////////////////////////////
    /**
     * 收到的订单页--大V接受/拒绝
     * @param params
     * @return
     */
    @RequestMapping(value = "/dvReceiveOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel dvReceiveOrder( @RequestBody DvReceiveOrderRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 收到的订单页--大V立即服务
     * @param params
     * @return
     */
    @RequestMapping(value = "/dvStartService", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel dvStartService( @RequestBody DvStartServiceRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 收到的订单页--大V同意退款/拒绝
     * @param params
     * @return
     */
    @RequestMapping(value = "/dvConfirmRefund", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel dvConfirmRefund( @RequestBody DvConfirmRefundRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /////////////////////////////////////////////////////////////////
    
    
    
}
