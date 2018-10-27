package com.honglu.quickcall.common.third.pay.alipay;

import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JSONUtil;
import com.honglu.quickcall.common.third.pay.alipay.mapiutil.AlipaySubmit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AlipayBatchTransfer {

    static final Logger logger = LoggerFactory.getLogger(AlipayBatchTransfer.class);

    /**
     * 支付宝批量转账接口
     *
     * @return
     */
    public static String createAliBatchTransfer(String detailData, String batchNum, String batchFee, String batchNo) {
        String notify = ResourceBundle.getBundle("thirdconfig").getString("pay_domain") + AlipayConfig.notify_url;
//        String batchNo = "BN" + DateUtils.getCurrentDateTime("yyyyMMddHHmmssSSS"); //批次号由接口调用房生成

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "batch_trans_notify");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("notify_url", notify);
        sParaTemp.put("email", AlipayConfig.seller_email);
        sParaTemp.put("account_name", AlipayConfig.account_name);
        sParaTemp.put("pay_date", DateUtils.getCurrentDateTime());
        sParaTemp.put("batch_no", batchNo);
        sParaTemp.put("batch_fee", batchFee);
        sParaTemp.put("batch_num", batchNum);
        //格式为：流水号1^收款方账号1^收款账号姓名1^付款金额1^备注说明1|流水号2^收款方账号2^收款账号姓名2^付款金额2^备注说明2。
        sParaTemp.put("detail_data", detailData);
        sParaTemp.put("extend_param", "busType^batchTransfer");//代表转账接口的回调
        //因是页面操作，手动设置编码格式
        AlipayConfig.input_charset = "GBK";

        logger.info("请求支付宝批量转账接口：" + JSONUtil.toJson(sParaTemp));
        //直接返回表单提交内容
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        logger.info("响应支付宝批量转账接口：" + sHtmlText);
        return sHtmlText;
    }
}
