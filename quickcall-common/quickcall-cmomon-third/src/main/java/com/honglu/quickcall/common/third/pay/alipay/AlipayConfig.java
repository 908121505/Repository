package com.honglu.quickcall.common.third.pay.alipay;

import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * 支付宝支付所需的必要参数
 * 理想情况下只需配置此处
 */
public class AlipayConfig {

    static Logger logger = LoggerFactory.getLogger(AlipayConfig.class);

    static {
        changePayConfig();
    }

    /**
     * 切换支付配置
     */
    static void changePayConfig() {
        ResourceBundle thirdconfig = ResourceBundle.getBundle("thirdconfig");
        String payConfigType = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "alipay_config_change");
        input_charset = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "alipay_input_charset");//統一编码
        notify_url = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "alipay_notify_url");//統一回调地址
        subject = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "alipay_subject");//統一标题
        if ("XM".equalsIgnoreCase(payConfigType)) {
            logger.info("厦门支付配置已启动");
            partner = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_partner");
            appid = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_appid");
            seller_email = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_seller_email");
            account_name = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_account_name");
            private_key = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_private_key");
            ali_public_key = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_ali_public_key");
            pay_sign_type = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_pay_sign_type");
            mapiKey = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_mapikey");
        } else if ("SH".equalsIgnoreCase(payConfigType)) {
            logger.info("上海支付配置已启动");
            partner = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_partner");
            appid = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_appid");
            seller_email = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_seller_email");
            account_name = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_account_name");
            private_key = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_private_key");
            ali_public_key = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_ali_public_key");
            pay_sign_type = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "SH_alipay_pay_sign_type");
            mapiKey = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "XM_alipay_mapikey");
        } else {
            logger.info("默认支付配置已启动");
            //使用配置文件中默认的配置
            initPropertiesConfig();
        }
        //判断是否测试订单
        subject = thirdconfig.containsKey("test_body_name") ? String.format("%s_%s", subject, thirdconfig.getString("test_body_name")) : subject;
        logger.info(String.format("%s:%s:%s:%s:%s:%s", payConfigType, input_charset, notify_url, subject, partner, pay_sign_type));
    }


    //利用properties配置初始化
    public static void initPropertiesConfig() {
        //切换成Redis获取
        ResourceBundle bundle = ResourceBundle.getBundle("thirdconfig");
        partner = bundle.getString("alipay_partner");
        appid = bundle.getString("alipay_appid");
        seller_email = bundle.getString("alipay_seller_email");
        account_name = bundle.getString("alipay_account_name");
        private_key = bundle.getString("alipay_private_key");
        ali_public_key = bundle.getString("alipay_ali_public_key");
        pay_sign_type = bundle.getString("alipay_pay_sign_type");
        notify_url = bundle.getString("alipay_notify_url");
        product_code = bundle.getString("alipay_product_code");
        input_charset = bundle.getString("alipay_input_charset");
        subject = "金豆充值";
        mapiKey = bundle.getString("alipay_mapi_key");
    }

    //开放平台接口请求地址
    public static final String serverUrl = "https://openapi.alipay.com/gateway.do";

    //Mapi接口请求地址
    public static final String serverMapiUrl = "https://mapi.alipay.com/gateway.do";

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner;// = "2088821208571055";

    public static String mapiKey;

    //appid
    public static String appid;// = "2017092108856045";

    //商户支付宝账号
    public static String seller_email;// = "service2@91mmliao.com";

    //商户真实姓名
    public static String account_name;// = "厦门迅熙网络科技有限公司";

    // 商户的私钥RSA
    //public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALFuxwNyvGGAh+529mK0unSNHhEe8cKMcyEKAWOi5OnyVbtr5Qo4978QQG3OfywNr65hEdNvBxXq74QBu+1SpqRC/BkfhrWpWpsWx1xyAdebJYAqqu7Cl60rDA2SFrDj1yYic/pjzWXRQrEpazFFfGkzedhRXD+0NwMbwjccnOE7AgMBAAECgYEAndFeip6fGEpunZiKhjkiL1DlkWzotJagQJ7ZpXnaCoxV3SKW0oThN7yI0iJ90v1Jad1FMb7rmn8hE1VHhkuhk7YjLU7eMcHT6TQ9Mrrh2VDxvQrQvpF4+NnBdmy7tYmYDlfdhxp8pDt4riM8gteO/IOSzE5amFHzqc2fPGC9h2ECQQDYUkgHXwzBXMnMPx8e2EJ6CxXas4DxlM6nycY5cZI8fMzuZpyTGchO/57konjUxrQgj3Vn1S1hA1Dg+FQxXdWlAkEA0fpqVmniNXQUvyosmdkyQGBJektkUhf05flJwsYZn7jSlv0WLZP7s39e8qXgYv/tK7kKvV6jh3sJKWkaPJblXwJAKN6y2SGuKUI8pigu2wcE2EYL5PwRGpOAz9ahauz4MhFqLoiYgHpYjARjotLHfXvlQFmaVvnBzxhvSXHhhkoyOQJAeu7SDO+GyWbBsr3dSOFZQ0lcG2ZCQLHX5kBRx9+1JsbNPCrGLj8CUK+UhhlJSoytbTizqfTbZrlIvt7SxoBNnwJBAK9gDd1wGD3qTtLUrtYDiSyEc0ryUcy/lkJENUcQ5kGJKj9tEb+Qzvb9kg6hq0BffGJ8tUdnlk2hEsGIixnj7lY=";
    public static String private_key;// = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMqxaFwZnRZgJCqEJjYNSdLBj0iUzuALUKKAjwd6dewfmDmOGdIH0sKx0OLM0Nz+2sihojyUxxYszG0PcOatiikmt3w1mx4UVt0bI4JjhRVTyothK6Gd6ajEpAQ821AHdUSiBWSUNzWgfWfKauS148vrx7ABTBV7AsGpIAWaB3VfAgMBAAECgYBw4yWQReOrVphN5ZOyfuwiqRK4BcjCFwstthPywk1y2JXbWD0hMKI9NPN1nUSmEHqhSwQkYWgP/ZtNElB66SQMj+H+j/77zzU5ZS8vfkz/9Jm6myBNUf/FUM4F6bbHTKkAHppYqTo6CaW8AHi+jMGLyaunroHsJ4HCHo/ItCbAQQJBAP7VyE1hB9ihVjCMkGKCdR6BujQZuy6AKlIzLsBuAd/d4Gy88BkB/lPalAa1JIy3OuRwhlzJ9gMT/TiaWwsOyDkCQQDLnptKA9u5Pz5n6ATYqfwh6jGfWEIogo06Vfdn+F2lFA+86SFGFHQCxPVUosOvamU9mjRCnSYp3u7bLFEeyLpXAkAQ0UDvwliHNlZ+y7f3mE5sXflQ2OXecrzYQeJy9o+PG5Bk/K8Hp3cGR6V/L9MiqjDrxHVJiFaA63/JjX42o5oZAkAKkROL9sFLTr3lVIQT3lZoSCgFFeZxBb1gfp+VNK1faLgcXJjOczdGAYSdvMic7M+OL9dwcYlOePQ6SYV4LICHAkEAq1ftCaMVZKVNP4hYonVYitmaiu/wgCfb4ktaLy9pRua9wBICLIZofRJcfi9TLeRKZ9LWcnkGZDH71/GuTCoo0g==";

    //支付宝的公钥  RSA
    //public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    public static String ali_public_key;// = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    //签名方式 (支付回调签名方式)
    public static String pay_sign_type;// = "RSA";

    public static String mapi_sign_type = "MD5";

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
     * 这里需要测试的话，需要用外网测试。https://www.ngrok.cc/   这里有免费和付费的，实际上，免费用一下就可以了。
     */
    public static String notify_url;// = "/accountCenter/pay/alipayNotify";
    //public static String notify_url = "http://xiaofanfight.viphk.ngrok.org/LiaoBanApi/ALiPay/AfterPayNotify";

    //商品的标题/交易标题/订单标题/订单关键字等。
    public static String subject;//= "金币充值";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    public static String product_code;// = "QUICK_MSECURITY_PAY";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset;// = "utf-8";
}
