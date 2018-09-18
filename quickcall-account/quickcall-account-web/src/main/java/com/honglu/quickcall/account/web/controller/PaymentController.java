package com.honglu.quickcall.account.web.controller;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.enums.TradeChannelEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.facade.exchange.request.*;
import com.honglu.quickcall.account.web.service.PaymentService;
import com.honglu.quickcall.account.web.util.CommonUtils;
import com.honglu.quickcall.account.web.util.MapUtils;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.api.util.JSONUtil;
import com.honglu.quickcall.common.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping(value = "/pay", method = RequestMethod.POST)
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private HttpServletRequest servletRequest;

    @RequestMapping("/mqTest")
    public WebResponseModel aliAppPay(PaySuccessMqRequest request) {
        return paymentService.execute(request);
    }

    /**
     * 支付宝支付(App、Wap)
     *
     * @param request
     * @return
     */
    @RequestMapping("/aliAppPay")
    public WebResponseModel aliAppPay(AlipayRechargeRequest request) {
        WebResponseModel responseModel = null;
        if (request.getPayType() == null || request.getPayType() <= 0) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        if (TradeChannelEnum.Alipay_Wap.getType().equals(request.getPayType())) { //支付宝H5支付
            request.setAccountFunctionType(AccountFunctionType.AlipayWapRecharge);
        } else if (TradeChannelEnum.Alipay_App.getType().equals(request.getPayType())) { //微信App支付
            request.setAccountFunctionType(AccountFunctionType.AlipayRecharge);
        } else {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        responseModel = paymentService.execute(request);
        return responseModel;
    }

    /**
     * 支付宝回调方法
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/alipayNotify")
    public String alipayNotify(HttpServletRequest httpServletRequest) {
        AlipayNotifyRequest request = new AlipayNotifyRequest();
        String result;
        try {
            //默认为支付回调
            request.setAccountFunctionType(AccountFunctionType.AlipayNotify);
            Map<String, String> map = MapUtils.GetInfoFromALiPay(httpServletRequest.getParameterMap());
            String requestMap = JSONUtil.toJson(map);
            logger.info("【alipayNotify】支付宝回调参数：", requestMap);
            request.setUrlEncodeParam(URLEncoder.encode(requestMap, "UTF-8"));
            WebResponseModel responseModel = paymentService.execute(request);
            if (responseModel.getCode().equalsIgnoreCase(BizCode.Success.code())) {
                result = "success";
            } else {
                result = "fail";
            }
        } catch (Exception e) {
            result = "fail";
            logger.error("【alipayNotify】支付宝接收通知异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 微信支付(App、H5)
     *
     * @param request
     * @return
     */
    @RequestMapping("/wechatAppPay")
    public WebResponseModel wechatAppPay(WechatPayRechargeRequest request) {
        WebResponseModel responseModel = null;
        if (request.getPayType() == null || request.getPayType() <= 0) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        if (TradeChannelEnum.Wechat_MWEB.getType().equals(request.getPayType())) { //微信H5支付
            request.setAccountFunctionType(AccountFunctionType.WechatMWEBRecharge);
        } else if (TradeChannelEnum.Wechat_App.getType().equals(request.getPayType())) { //微信App支付
            request.setAccountFunctionType(AccountFunctionType.WechatAppRecharge);
        } else {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        request.setClientIp(CommonUtils.getAddrIP(servletRequest));
        responseModel = paymentService.execute(request);
        return responseModel;
    }

    /**
     * 微信支付成功回调
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/wechatPayNotify")
    public String wechatPayNotify(HttpServletRequest httpServletRequest) {
        String result;
        try {
            WechatPayNotifyRequest request = new WechatPayNotifyRequest();
            request.setWechatParamMap(MapUtils.getWechatParams(httpServletRequest));
            WebResponseModel responseModel = paymentService.execute(request);
            if (responseModel.getCode().equalsIgnoreCase(BizCode.Success.code())) {
                result = "success";
            } else {
                result = "fail";
            }
        } catch (Exception e) {
            result = "fail";
            logger.error("【wechatPayNotify】微信接收通知异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 交易退款接口(内部接口)
     *
     * @param request
     * @return
     */
    @RequestMapping("/tradeRefund")
    public WebResponseModel tradeRefund(ThirdOrderRefundRequest request) {
        WebResponseModel responseModel = null;
        if (!StringUtil.isNull(request.getOutOrderNo()) && StringUtil.isNull(request.getPayType()) && request.getRefundAmount() <= 0) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        responseModel = paymentService.execute(request);
        return responseModel;
    }

    /**
     * 第三方平台订单查询(支付宝、微信)
     *
     * @param request
     * @return
     */
    @RequestMapping("/thirdOrderQuery")
    public WebResponseModel thirdOrderQuery(OrderQueryRequest request) {
        WebResponseModel responseModel = null;
        if (!StringUtil.isNull(request.getOrderNo()) && !StringUtil.isNull(request.getPayType())) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        responseModel = paymentService.execute(request);
        return responseModel;
    }

    /**
     * 转账接口(批量转账、单笔转账)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "batchTransfer", produces = "application/json;charset=UTF-8")
    public WebResponseModel batchTransfer(@RequestBody AlipayBatchTransferRequest request) {
        WebResponseModel responseModel;
        if (!StringUtil.isNull(request.getBatchNo()) || request.getBatchTransfers().size() < 1) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), "参数不能为空");
            return responseModel;
        }
        if (TransferTypeEnum.MF.getType().toString().equalsIgnoreCase(request.getInterfaceType())) {
            //免费单笔转账接口
            request.setAccountFunctionType(AccountFunctionType.AlipaySingleTransfer);
        } else if (TransferTypeEnum.SF.getType().toString().equalsIgnoreCase(request.getInterfaceType())) {
            //收费批量操作接口
            request.setAccountFunctionType(AccountFunctionType.AlipayBatchTransfer);
        } else {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), AccountBizReturnCode.ParamError.desc());
            return responseModel;
        }
        responseModel = paymentService.execute(request);
        return responseModel;
    }

    /**
     * 统一下单接口 - 适用于应用内支付(ApplePay)
     *
     * @return
     */
    @RequestMapping("/unifiedOrder")
    public WebResponseModel unifiedOrder(UnifiedOrderRequest request) {
        WebResponseModel responseModel;
        //验证参数
        if (request.getUserId() <= 0 || request.getPayAmount() <= 0 || !StringUtil.isNull(request.getPayType())) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), "参数不能为空");
            return responseModel;
        }

        //支付方式是否存在 后续继续增加支付方式
        if (TradeChannelEnum.ApplePay.toString().equalsIgnoreCase(request.getPayType())) {
            request.setAccountFunctionType(AccountFunctionType.PayUnifiedOrder);
        } else {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), "支付方式不存在");
            return responseModel;
        }
        responseModel = paymentService.execute(request);
        return responseModel;
    }

    /**
     * 统一下单支付回调接口
     */
    @RequestMapping("/unifiedPayNotify")
    public WebResponseModel unifiedPayNotify(HttpServletRequest request) {
        WebResponseModel responseModel = null;
        String payOrderNo = request.getParameter("payOrderNo");
        String payType = request.getParameter("payType");
        //验证参数
        if (!StringUtil.isNull(payOrderNo) || !StringUtil.isNull(payType)) {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), "参数不能为空");
            return responseModel;
        }
        //判断支付方式
        if (TradeChannelEnum.ApplePay.toString().equalsIgnoreCase(payType)) {
            String receiptData = request.getParameter("receiptData");
            responseModel = paymentService.execute(new ApplePayNotifyRequest(payOrderNo, receiptData, AccountFunctionType.ApplePayNotifyOrder));
        } else {
            responseModel = new WebResponseModel(AccountBizReturnCode.ParamError.code(), AccountBizReturnCode.ParamError.desc(), "支付方式不存在");
        }
        return responseModel;
    }
}
