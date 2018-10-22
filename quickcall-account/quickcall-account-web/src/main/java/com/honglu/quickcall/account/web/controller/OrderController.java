package com.honglu.quickcall.account.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.exchange.request.*;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.web.service.IOrderInfoService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 获取主播开启产品
     * @param params
     * @return
     */
    @RequestMapping(value = "/dvProduct", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryDaVProduct(/*  @RequestBody  */OrderDaVSkillRequest params) {
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
    public WebResponseModel saveOrder( /* @RequestBody */ OrderSaveRequest params) {
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
    public WebResponseModel queryReceiveOrderList( /* @RequestBody */ OrderReceiveOrderListRequest params) {
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
    public WebResponseModel querySendOrderList( /* @RequestBody */ OrderSendOrderListRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    
    /////////////////////////////////////////////////////////////////
    /**
     * 发起的订单页--取消订单
     * @param params
     * @return
     */
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel cancelOrder( /* @RequestBody */ CancelOrderRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 发起的订单页--去支付
     * @param params
     * @return
     */
    @RequestMapping(value = "/detailOrder", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel detailOrder( /* @RequestBody */ DetailOrderRequest params) {
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
    public WebResponseModel payOrder( /* @RequestBody */ PayOrderRequest params) {
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
    public WebResponseModel applayRefund( /* @RequestBody */ ApplayRefundRequest params) {
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
    public WebResponseModel confirmOrder( /* @RequestBody */ ConfirmOrderRequest params) {
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
    public WebResponseModel dvReceiveOrder( /* @RequestBody */ DvReceiveOrderRequest params) {
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
    public WebResponseModel dvStartService( /* @RequestBody */ DvStartServiceRequest params) {
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
    public WebResponseModel dvConfirmRefund( /* @RequestBody */ DvConfirmRefundRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /////////////////////////////////////////////////////////////////
    /**
     * 查询是否有进行中订单数量
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryIngOrderCount", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryIngOrderCount( /* @RequestBody */ QueryIngOrderCountRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }
    /**
     * 查询是否有进行中订单数量
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryRefundReason", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryRefundReason( /* @RequestBody */ QueryRefundReasonRequest params) {
    	WebResponseModel response = orderInfoService.execute(params);
    	return response;
    }

    /**
     * 订单评价页面
     * @param params
     * @return
     */
    @RequestMapping(value = "/orderEvaluation", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel orderEvaluation(OrderEvaluationRequest params) {
        LOGGER.info("orderWeb order orderEvaluation request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = new WebResponseModel();
        if (params.getOrderId() == null) {
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg(UserBizReturnCode.paramError.desc());
            return response;
        }
        response = orderInfoService.execute(params);
        LOGGER.info("orderWeb user orderEvaluation response data : " + JSONObject.toJSONString(response));
        return response;
    }

    /**
     * 提交订单评价
     * @param params
     * @return
     */
    @RequestMapping(value = "/orderEvaluationSubmit", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel orderEvaluationSubmit(OrderEvaluationSubmitRequest params) {
        LOGGER.info("orderWeb order orderEvaluationSubmit request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = new WebResponseModel();
        response.setCode(UserBizReturnCode.paramError.code());
        if (params.getOrderId() == null || params.getCustomerId() == null) {
            response.setMsg(UserBizReturnCode.paramError.desc());
            return response;
        }
        if(params.getLabelIds() != null && params.getLabelIds().length > 3){
            response.setMsg("最多选择3个标签");
            return response;
        }
        response = orderInfoService.execute(params);
        LOGGER.info("orderWeb user orderEvaluationSubmit response data : " + JSONObject.toJSONString(response));
        return response;
    }

}
