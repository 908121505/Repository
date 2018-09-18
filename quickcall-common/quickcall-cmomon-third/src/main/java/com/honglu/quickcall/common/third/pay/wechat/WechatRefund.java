package com.honglu.quickcall.common.third.pay.wechat;

import com.github.wxpay.sdk.WXPay;
import com.honglu.quickcall.common.third.pay.wechat.model.WechatConfig;
import com.honglu.quickcall.common.third.pay.wechat.model.WechatPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WechatRefund {
    private static final Logger logger = LoggerFactory.getLogger(WechatRefund.class);

    private static WXPay wxpay;

    static {
        try {
            wxpay = new WXPay(new WechatPayConfig());
        } catch (Exception e) {
            logger.error("【WechatRefund】初始化微信证书异常：" + e.getMessage());
        }
    }

    public static Map<String, String> createWechatRefund(String out_trade_no, String total_fee) throws Exception {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", total_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", WechatConfig.mch_id);

        Map<String, String> refundResult = wxpay.refund(data);

        return refundResult;
    }
}
