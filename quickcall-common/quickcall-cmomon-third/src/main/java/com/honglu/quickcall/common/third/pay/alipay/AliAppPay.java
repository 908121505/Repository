package com.honglu.quickcall.common.third.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class AliAppPay {

    private static final Logger logger = LoggerFactory.getLogger(AliAppPay.class);

    /**
     * 拼接支付参数返回
     *
     * @param payOrderNo
     * @param payAmount
     * @return
     * @throws AlipayApiException
     */
    public static Map<String, String> createAliAppPay(String payOrderNo, Integer payAmount) throws Exception {
        Map<String,String> paramMap = new HashMap<String,String>();
        String notify = URLEncoder.encode(ResourceBundle.getBundle("thirdconfig").getString("pay_domain") + AlipayConfig.notify_url, "UTF-8");
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.serverUrl,
                AlipayConfig.appid, AlipayConfig.private_key, "json",
                AlipayConfig.input_charset, AlipayConfig.ali_public_key, AlipayConfig.pay_sign_type);

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(AlipayConfig.subject);
        model.setSubject(AlipayConfig.subject);
        model.setOutTradeNo(payOrderNo);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(payAmount.toString());
        model.setProductCode(AlipayConfig.product_code);
        request.setBizModel(model);
        request.setNotifyUrl(notify);

        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        logger.info(String.format("订单号：%s，拼接内容：%s", payOrderNo, response.getBody()));
        paramMap.put("orderStr",response.getBody());
        paramMap.put("appId",response.getSellerId());
        return paramMap;
    }
}
