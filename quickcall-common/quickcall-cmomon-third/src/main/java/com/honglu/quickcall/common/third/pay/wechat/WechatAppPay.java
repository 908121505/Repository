package com.honglu.quickcall.common.third.pay.wechat;

import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.third.pay.wechat.model.Unifiedorder;
import com.honglu.quickcall.common.third.pay.wechat.model.UnifiedorderResult;
import com.honglu.quickcall.common.third.pay.wechat.model.WXPayResult;
import com.honglu.quickcall.common.third.pay.wechat.model.WechatConfig;
import com.honglu.quickcall.common.third.pay.wechat.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

public class WechatAppPay {

    private static final Logger logger = LoggerFactory.getLogger(WechatAppPay.class);

    public static HashMap<String, Object> createWxPayParam(String outOrderNo, Integer rechargeAmount) throws Exception {
        String notifyUrl = ResourceBundle.getBundle("thirdconfig").getString("pay_domain") + WechatConfig.notify_url;
        Integer payMoney = BigDecimal.valueOf(rechargeAmount).multiply(BigDecimal.valueOf(100)).intValue();
        String nonce_str = WxUtil.getRandomString(20);
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
        wxparameters.put("spbill_create_ip", WechatConfig.spbill_create_ip);//终端IP  用户端实际ip
        wxparameters.put("notify_url", notifyUrl);//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        wxparameters.put("trade_type", "APP");//支付类型  只有一个参数APP
        wxparameters.put("time_start", DateUtils.getCurrentDateTime());//订单开始时间
        //订单关闭时间 在开始时间的基础上后推30分钟
        wxparameters.put("time_expire", DateUtils.formatDate(DateUtils.getCurrentDate().getTime() + 30 * DateUtils.MINUTE));

        //生成签名
        String sign = WxUtil.createSign("UTF-8", wxparameters);

        //对象进行存储
        Unifiedorder unifiedorder = new Unifiedorder(WechatConfig.appid, WechatConfig.mch_id, nonce_str, sign, WechatConfig.body, outOrderNo, payMoney, WechatConfig.spbill_create_ip, notifyUrl, "APP");

        //转成xml形式
        String xmlInfo = HttpXmlUtil.xmlInfo(unifiedorder);

        if (xmlInfo != "") {
            logger.info("微信XML的请求参数:" + xmlInfo);

            String weixinPost = HttpXmlUtil.httpsRequest(WechatConfig.wxUrl, "POST", xmlInfo).toString();//请求微信

            logger.info("微信支付的反馈信息为:" + weixinPost);

            UnifiedorderResult unifiedorderResult = ParseXMLUtils.jdomParseXml(weixinPost);//解析微信的反馈

            if (unifiedorderResult != null) {
                if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
                    //开始拼接App调起微信的参数
                    HashMap<String, Object> wxAppparameters = new HashMap<String, Object>();
                    wxAppparameters.put("appid", unifiedorderResult.getAppid());
                    wxAppparameters.put("partnerid", unifiedorderResult.getMch_id());
                    wxAppparameters.put("prepayid", unifiedorderResult.getPrepay_id());
                    wxAppparameters.put("package", WechatConfig.PACKAGE);
                    wxAppparameters.put("noncestr", WxUtil.getRandomString(20));
                    wxAppparameters.put("timestamp", String.valueOf(new Date().getTime()).substring(0, 10));

                    //生成签名
                    String signApp = WxUtil.createSign("UTF-8", wxAppparameters);
                    wxAppparameters.put("sign", signApp);
                    return wxAppparameters;
                } else {
                    logger.error("错误原因为：" + unifiedorderResult.getReturn_msg());
                    return null;
                }
            } else {
                logger.error("服务端请求微信的返回值异常。");
                return null;
            }
        } else {
            logger.error("微信参数转成的xml形式错误");
            return null;
        }
    }

    /**
     * 检测微信异步反馈是否真实
     *
     * @param inputString
     * @return null 验证失败  不为null即成功
     */
    public static HashMap<String, Object> checkWxPayParam(String inputString) throws Exception {

        logger.info("----[微信回调]接收到的报文---" + inputString);

        if (inputString != null && inputString != "") {
            //解析微信异步通知的内容
            WXPayResult wxPayResult = JdomParseXmlUtils.getWXPayResult(inputString);

            //忽略大小写进行比对    此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
            if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code())) {//SUCCESS/FAIL

                HashMap<String, Object> parameters = new HashMap<String, Object>();

                parameters.put("appid", wxPayResult.getAppid());//应用ID

                parameters.put("attach", wxPayResult.getAttach());//商家数据包

                parameters.put("bank_type", wxPayResult.getBank_type());//付款银行

                parameters.put("cash_fee", wxPayResult.getCash_fee());//现金支付金额

                parameters.put("fee_type", wxPayResult.getFee_type());//**货币种类

                parameters.put("is_subscribe", wxPayResult.getIs_subscribe());

                parameters.put("mch_id", wxPayResult.getMch_id());//商户号

                parameters.put("nonce_str", wxPayResult.getNonce_str());//随机字符串

                parameters.put("openid", wxPayResult.getOpenid());//用户标识

                parameters.put("out_trade_no", wxPayResult.getOut_trade_no());//商户订单号

                if ("SUCCESS".equalsIgnoreCase(wxPayResult.getResult_code()) ||
                        "REFUND".equalsIgnoreCase(wxPayResult.getResult_code()) ||
                        "CLOSED".equalsIgnoreCase(wxPayResult.getResult_code())) {

                    parameters.put("result_code", wxPayResult.getResult_code());//业务结果

                    parameters.put("return_code", wxPayResult.getReturn_code());//返回状态码

                    parameters.put("time_end", wxPayResult.getTime_end());//支付完成时间

                    parameters.put("total_fee", wxPayResult.getTotal_fee());//总金额-分为单位

                    parameters.put("trade_type", wxPayResult.getTrade_type());//交易类型

                    parameters.put("transaction_id", wxPayResult.getTransaction_id());//微信支付订单号

                } else if ("FAIL".equalsIgnoreCase(wxPayResult.getResult_code())) {
                    parameters.put("err_code", wxPayResult.getErr_code());//错误返回的信息描述
                    parameters.put("err_code_des", wxPayResult.getErr_code_des());//错误返回的信息描述
                    logger.error("微信支付错误，错误码为：" + wxPayResult.getErr_code() + "错误详情为：" + wxPayResult.getErr_code_des());
                }

                //反校验签名
                String sign = WxUtil.createSign("UTF-8", parameters);

                if (sign.equals(wxPayResult.getSign())) {//签名一致

                    //验证一些基础参数是否一致
                    if (WechatConfig.mch_id.equals(parameters.get("mch_id"))) {

                        if (WechatConfig.appid.equals(parameters.get("appid"))) {//应用id

                            if ("SUCCESS".equalsIgnoreCase(parameters.get("result_code").toString())) {
                                return parameters;
                            } else {
                                logger.error("微信支付通知结果不为SUCCESS。");
                                return null;
                            }
                        } else {
                            logger.error("应用appid不一致。");
                            return null;
                        }
                    } else {
                        logger.error("商户mch_id不一致。");
                        return null;
                    }
                } else {
                    logger.error("反校验签名不一致。");
                    return null;
                }
            } else {
                logger.error("微信回调的通信标识验证失败。");
                return null;
            }
        } else {
            logger.error("未收到微信异步回调的报文。");
            return null;
        }

    }
}
