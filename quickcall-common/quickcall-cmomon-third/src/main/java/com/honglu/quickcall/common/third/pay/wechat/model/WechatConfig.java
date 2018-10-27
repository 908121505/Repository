package com.honglu.quickcall.common.third.pay.wechat.model;

import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.third.pay.alipay.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ResourceBundle;

public class WechatConfig {

    static Logger logger = LoggerFactory.getLogger(WechatConfig.class);

    static {
        //changePayConfig();
    }

    static void changePayConfig() {
        ResourceBundle thirdconfig = ResourceBundle.getBundle("thirdconfig");
        String payConfigType = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "wechat_pay_config");
        notify_url = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "wechat_notify_url");
        body = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "wechat_body");
        spbill_create_ip = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "wechat_spbill_create_ip");
        if ("XM".equalsIgnoreCase(payConfigType)) {
            logger.info("厦门支付配置已启动");
            appid = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_wechat_appid");
            mch_id = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_wechat_mch_id");
            Key = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_wechat_Key");
        } else if ("SH".equalsIgnoreCase(payConfigType)) {
            logger.info("上海支付配置已启动");
            appid = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_wechat_appid");
            mch_id = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_wechat_mch_id");
            Key = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_wechat_Key");
        } else {
            logger.info("默认支付配置已启动");
            //使用配置文件中默认的配置
            initPropertiesConfig();
        }
        //判断是否测试订单
        body = thirdconfig.containsKey("test_body_name") ? String.format("%s_%s", body, thirdconfig.getString("test_body_name")) : body;
    }

    static void initPropertiesConfig() {
        ResourceBundle bundle = ResourceBundle.getBundle("thirdconfig");
        appid = bundle.getString("wechat_appid");
        mch_id = bundle.getString("wechat_mch_id");
        Key = bundle.getString("wechat_Key");
        notify_url = bundle.getString("wechat_notify_url");
        body = "金豆充值";
        spbill_create_ip = bundle.getString("wechat_spbill_create_ip");
    }


    //应用ID
    public static String appid;// = "wx3779563af4ee476b";
    //商户号
    public static String mch_id;// = "1489682792";

    /**
     * 设置地址在微信商户平台，key设置路径：微信商户平台-->账户设置-->API安全-->密钥设置
     * 密码生成地址：http://www.sexauth.com/
     */
//	public static String Key = "58hw1Hx6LSmCgqB56sWL2HydKY55YHt1";
    public static String Key;// = "cLdDzWND7ZTKbUYJlz3P0qOBUkQnGa3w";


    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     */
    public static String notify_url;// = "/accountCenter/pay/wechatPayNotify";

    /**
     * 商品描述交易字段格式根据不同的应用场景按照以下格式：
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     */
    public static String body;// = "金币充值";


    //客户端ip，固定为此值
    public static String spbill_create_ip;// = "127.0.0.1";

    //固定值 Sign=WXPay
    public static String PACKAGE = "Sign=WXPay";

    /**
     * 微信统一下单接口请求地址   固定值
     */
    public static String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
