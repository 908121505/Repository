package com.honglu.quickcall.common.third.pay.wechat;

import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.third.pay.alipay.AlipayConfig;
import com.honglu.quickcall.common.third.pay.wechat.model.Unifiedorder;
import com.honglu.quickcall.common.third.pay.wechat.model.UnifiedorderResult;
import com.honglu.quickcall.common.third.pay.wechat.utils.HttpXmlUtil;
import com.honglu.quickcall.common.third.pay.wechat.utils.ParseXMLUtils;
import com.honglu.quickcall.common.third.pay.wechat.model.WechatConfig;
import com.honglu.quickcall.common.third.pay.wechat.utils.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class WechatH5Pay {
    private static final Logger logger = LoggerFactory.getLogger(WechatH5Pay.class);

    public static String createWxMWEBUrl(String outOrderNo, Integer rechargeAmount, String ip) throws Exception {
        //请求支付的客户端IP
        String remoteAddr = ip == "" || ip == null ? WechatConfig.spbill_create_ip : ip;
        String nonce_str = WxUtil.getRandomString(20);
        Integer payMoney = BigDecimal.valueOf(rechargeAmount).multiply(BigDecimal.valueOf(100)).intValue();
        String domain = ResourceBundle.getBundle("thirdconfig").getString("pay_domain");
        //用于App中使用H5支付
        String sceneInfo = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"" + domain + "\",\"wap_name\": \"" + WechatConfig.body + "\"}}";
        //开始时间及结束时间
        String timeStart = DateUtils.getCurrentDateTime("yyyyMMddHHmmss");
        String timeExpire = DateUtils.formatDate(DateUtils.getCurrentDate().getTime() + 30 * DateUtils.MINUTE,"yyyyMMddHHmmss");

        //声明统一下单待签名字符串的map
        HashMap<String, Object> wxparameters = new HashMap<String, Object>();
        wxparameters.put("appid", WechatConfig.appid);//微信开放平台审核通过的应用APPID
        wxparameters.put("mch_id", WechatConfig.mch_id);//微信支付分配的商户号
        wxparameters.put("nonce_str", nonce_str);//随机字符串，不长于32位。
        wxparameters.put("body", WechatConfig.body);
        wxparameters.put("out_trade_no", outOrderNo);//商户订单号
        /**
         * 订单总金额  传入的金额暂定为元，则需要*100变成分  int类型
         */
        wxparameters.put("total_fee", payMoney);//订单总金额 接口中参数支付金额单位为【分】，参数值不能带小数。
        wxparameters.put("spbill_create_ip", remoteAddr);// 终端IP  用户端实际ip
        wxparameters.put("notify_url", domain + WechatConfig.notify_url);//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        wxparameters.put("trade_type", "MWEB");//支付类型  MWEB为H5支付
        wxparameters.put("scene_info", sceneInfo);//支付类型  只有一个参数APPs
        wxparameters.put("time_start", timeStart);//订单开始时间
        //订单关闭时间 在开始时间的基础上后推30分钟
        wxparameters.put("time_expire", timeExpire);

        //生成签名
        String sign = WxUtil.createSign("UTF-8", wxparameters);
        //对象进行存储
        Unifiedorder unifiedorder = new Unifiedorder(WechatConfig.appid, WechatConfig.mch_id, nonce_str,
                sign, WechatConfig.body, outOrderNo, payMoney, remoteAddr,//WechatConfig.spbill_create_ip
                domain + WechatConfig.notify_url, "MWEB", sceneInfo, timeStart, timeExpire);

        //转成xml形式
        String xmlInfo = HttpXmlUtil.xmlInfo(unifiedorder);
        if (xmlInfo != "") {
            logger.info("微信XML的请求参数:" + xmlInfo);
            String weixinPost = HttpXmlUtil.httpsRequest(WechatConfig.wxUrl, "POST", xmlInfo).toString();//请求微信
            logger.info("微信支付的反馈信息为:" + weixinPost);

            UnifiedorderResult unifiedorderResult = ParseXMLUtils.jdomParseXml(weixinPost);//解析微信的反馈

            System.out.println("MWEB返回：" + unifiedorderResult);

            if (unifiedorderResult != null) {
                if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
                    return unifiedorderResult.getMweb_url();
                }
            }
        }

        return null;
    }
}
