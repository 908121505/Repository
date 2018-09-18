package com.honglu.quickcall.common.third.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.honglu.quickcall.common.api.util.JSONUtil;

import java.math.BigDecimal;

public class AlipaySingleTransfer {
    /**
     * 支付宝单笔转账接口
     *
     * @param orderNo
     * @param payeeAccount
     * @param transferAmount
     * @param remark
     * @return
     * @throws AlipayApiException
     */
    public static AlipayFundTransToaccountTransferResponse createSingleTransfer(String orderNo, String payeeAccount, String transferAmount, String remark) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.appid,
                AlipayConfig.private_key, "json", AlipayConfig.input_charset, AlipayConfig.ali_public_key, AlipayConfig.pay_sign_type);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        //组装请求参数
        StringBuffer buffer = new StringBuffer("{\"payee_type\":\"ALIPAY_LOGONID\",");
        buffer.append(String.format("\"payee_account\":\"%s\",", payeeAccount));
        buffer.append(String.format("\"out_biz_no\":\"%s\",", orderNo));
        buffer.append(String.format("\"amount\":\"%s\",", BigDecimal.valueOf(Double.parseDouble(transferAmount)).doubleValue()));
        buffer.append(String.format("\"remark\":\"%s\"}", "提现"));
        request.setBizContent(buffer.toString());
        AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
        return response;
    }
}