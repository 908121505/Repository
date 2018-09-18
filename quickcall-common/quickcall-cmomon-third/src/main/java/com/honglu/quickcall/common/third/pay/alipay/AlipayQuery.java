package com.honglu.quickcall.common.third.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.honglu.quickcall.common.third.pay.alipay.mapiutil.AlipaySubmit;

import java.util.HashMap;
import java.util.Map;

public class AlipayQuery {

    static AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.serverUrl,
            AlipayConfig.appid, AlipayConfig.private_key, "json",
            AlipayConfig.input_charset, AlipayConfig.ali_public_key, AlipayConfig.pay_sign_type);

    /**
     * 创建订单交易查询
     *
     * @param outOrderNo
     * @return
     * @throws Exception
     */
    public static AlipayTradeQueryResponse createOrderQuery(String outOrderNo) throws Exception {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent(String.format("{\"out_trade_no\":\"%s\"}", outOrderNo));
        return isPayment(request);
    }

    /**
     * 创建订单转账查询
     *
     * @param outBizNo
     * @return
     * @throws AlipayApiException
     */
    public static AlipayFundTransOrderQueryResponse createTransferQuery(String outBizNo) throws AlipayApiException {
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizContent(String.format("{\"out_biz_no\":\"%s\"}", outBizNo));
        AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 创建支付宝账务明细分页查询接口
     *
     * @return
     */
    public static String createAlipayAccountPageQuery(String startTime, String endTime, String pageIndex) throws Exception {
        //把请求参数打包成数组
               Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "account.page.query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("page_no", pageIndex);
        sParaTemp.put("gmt_start_time", startTime);
        sParaTemp.put("gmt_end_time", endTime);

        String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);

        return sHtmlText;
    }

    /**
     * 查询支付宝对账单下载地址
     *
     * @param billType
     * @param billDate
     * @return
     * @throws AlipayApiException
     */
    public static AlipayDataDataserviceBillDownloadurlQueryResponse createAlipayBillDownloadUrlQuery(String billType, String billDate) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent(String.format("{\"bill_type\":\"%s\",\"bill_date\":\"%s\"}", billType, billDate));
        AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
        return response;
    }


    static AlipayTradeQueryResponse isPayment(AlipayTradeQueryRequest request) throws Exception {
        Thread.sleep(1000);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if (!"TRADE_SUCCESS".equalsIgnoreCase(response.getTradeStatus()) &&
                !"TRADE_CLOSED".equalsIgnoreCase(response.getTradeStatus()) &&
                !"ACQ.TRADE_NOT_EXIST".equalsIgnoreCase(response.getSubCode())) {
            Thread.sleep(3000);
            response = alipayClient.execute(request);
        }
        return response;
    }
}
