package com.honglu.quickcall.common.third.pay.wechat;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.honglu.quickcall.common.third.pay.wechat.model.WechatPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class WechatPayQuery {
    private static final Logger logger = LoggerFactory.getLogger(WechatPayQuery.class);
    private static WXPay wxpay;

    static {
        try {
            wxpay = new WXPay(new WechatPayConfig());
        } catch (Exception e) {
            logger.error("【WechatRefund】初始化微信证书异常：" + e.getMessage());
        }
    }

    /*
     * 微信支付订单查询返回值：
     * {nonce_str=Drugr1cylN0PjFaK, out_trade_no=SH20171220203039055,
     * trade_state=NOTPAY, appid=wx3779563af4ee476b, sign=7F4BBDBEDD4681E84BC827E86B0B0421,
     * trade_state_desc=订单未支付, return_msg=OK, result_code=SUCCESS, mch_id=1489682792, return_code=SUCCESS}
     *
     * */

    public static Map<String, String> createOrderQuery(String outOrderNo) throws Exception {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outOrderNo);
        return isPayment(data);
    }

    private static Map<String, String> isPayment(HashMap<String, String> data) throws Exception {
        Thread.sleep(2000);
        Map<String, String> map = wxpay.orderQuery(data);
        if (!"SUCCESS".equalsIgnoreCase(map.get("trade_state")) &&
//               !"NOTPAY".equalsIgnoreCase(map.get("trade_state")) &&  //未支付状态需再次校验
                !"ORDERNOTEXIST".equalsIgnoreCase(map.get("err_code")) &&
                !"REFUND".equalsIgnoreCase(map.get("trade_state"))) {
            Thread.sleep(1000);
            map = wxpay.orderQuery(data);
        }
        return map;
    }
}
