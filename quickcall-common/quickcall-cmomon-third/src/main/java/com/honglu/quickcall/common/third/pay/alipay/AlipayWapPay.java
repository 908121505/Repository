package com.honglu.quickcall.common.third.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.honglu.quickcall.common.api.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class AlipayWapPay {


    static final Logger logger = LoggerFactory.getLogger(AlipayWapPay.class);

    /**
     * 创建支付宝Wap支付
     */
    public static String createAliWapPay(String payOrderNo, Integer payAmount, String returnUrl) throws Exception {
        ResourceBundle thirdconfig = ResourceBundle.getBundle("thirdconfig");
        String notify = thirdconfig.getString("pay_domain") + AlipayConfig.notify_url;
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.serverUrl, AlipayConfig.appid, AlipayConfig.private_key,
                "json", AlipayConfig.input_charset, AlipayConfig.ali_public_key, AlipayConfig.pay_sign_type); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        //拼接参数供H5页面使用
        String returnParam = String.format("&orderNo=%s&type=zhifubao", payOrderNo);

        alipayRequest.setReturnUrl(returnUrl == null ? "" : returnUrl + returnParam);
        alipayRequest.setNotifyUrl(notify);//在公共参数中设置回跳和通知地址

        //支付宝订单有效期为30分钟
        StringBuffer buffer = new StringBuffer("{\"product_code\":\"QUICK_WAP_PAY\",\"timeout_express\":\"1m\",");
        buffer.append(String.format("\"out_trade_no\":\"%s\",", payOrderNo));
        buffer.append(String.format("\"total_amount\":\"%s\",", payAmount));
        buffer.append(String.format("\"subject\":\"%s\"}", AlipayConfig.subject));
        alipayRequest.setBizContent(buffer.toString());//填充业务参数

        String form = "";
        try {
            logger.info("支付宝SDK请求参数：" + JSONUtil.toJson(alipayRequest));
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            logger.info("支付宝SDK响应参数：" + form);
        } catch (AlipayApiException e) {
            logger.info("支付宝H5支付异常：" + e.getMessage());
        }
        //返回自提交表单
        return form;
    }
}
