package com.honglu.quickcall.common.third.pay.wechat.model;

import com.github.wxpay.sdk.WXPayConfig;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.third.pay.wechat.WechatPayQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class WechatPayConfig implements WXPayConfig {

    private static final Logger logger = LoggerFactory.getLogger(WechatPayConfig.class);

    @Override
    public String getAppID() {
        return WechatConfig.appid;
    }

    @Override
    public String getMchID() {
        return WechatConfig.mch_id;
    }

    @Override
    public String getKey() {
        return WechatConfig.Key;
    }

    @Override
    public InputStream getCertStream() {
        String cert_url = JedisUtil.get(RedisKeyConstants.SYS_PARAM + "wechat_pay_cert_url");
        return WechatPayConfig.class.getClassLoader().getResourceAsStream(cert_url);
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
