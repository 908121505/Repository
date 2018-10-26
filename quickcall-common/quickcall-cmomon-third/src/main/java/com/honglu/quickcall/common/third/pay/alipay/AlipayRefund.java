package com.honglu.quickcall.common.third.pay.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlipayRefund {
    private static final Logger logger = LoggerFactory.getLogger(AlipayRefund.class);

    public static AlipayTradeRefundResponse createAlipayRefund(String payOrderNo, Integer refundAmount) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.serverUrl,
                AlipayConfig.appid, AlipayConfig.private_key, "json",
                AlipayConfig.input_charset, AlipayConfig.ali_public_key, AlipayConfig.pay_sign_type);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        String requestNo = String.format("REFUND%s", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

        StringBuffer buffer = new StringBuffer("{\"refund_reason\":\"交易退款\",");
        buffer.append(String.format("\"out_trade_no\":\"%s\",", payOrderNo));
        buffer.append(String.format("\"refund_amount\":\"%s\",", refundAmount));
        buffer.append(String.format("\"out_request_no\":\"%s\"}", requestNo));

        request.setBizContent(buffer.toString());

        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return response;
    }
}
