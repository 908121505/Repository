package com.honglu.quickcall.common.third.pay.wechat.model;

public class Unifiedorder {
    private String appid;//微信分配的公众账号ID（企业号corpid即为此appId）,例如：wxd678efh567hg6787
    private String mch_id;//商户id
    private String nonce_str;//随机字符串:数字+大写字母的组合，32位
    private String sign;//签名
    private String body;//商品或支付单简要描述
    private String out_trade_no;//商户系统内部的订单号
    private int total_fee;//总金额
    private String spbill_create_ip;//APP和网页支付提交[用户端ip]，Native支付填调用微信支付API的机器IP。
    private String notify_url;//接收微信支付异步通知回调地址
    private String trade_type;//交易类型:JSAPI，NATIVE，APP
    private String scene_info;//H5支付必传
    private String time_start;//订单创建时间
    private String time_expire;//订单关闭时间

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getScene_info() {
        return scene_info;
    }

    public void setScene_info(String scene_info) {
        this.scene_info = scene_info;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public String getBody() {
        return body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public Unifiedorder(String appid, String mchId, String nonceStr,
                        String sign, String body, String outTradeNo, int totalFee,
                        String spbillCreateIp, String notifyUrl, String tradeType) {
        super();
        this.appid = appid;
        mch_id = mchId;
        nonce_str = nonceStr;
        this.sign = sign;
        this.body = body;
        out_trade_no = outTradeNo;
        total_fee = totalFee;
        spbill_create_ip = spbillCreateIp;
        notify_url = notifyUrl;
        trade_type = tradeType;
    }

    public Unifiedorder(String appid, String mchId, String nonceStr,
                        String sign, String body, String outTradeNo, int totalFee,
                        String spbillCreateIp, String notifyUrl, String tradeType, String sceneInfo,
                        String time_start, String time_expire) {
        super();
        this.appid = appid;
        mch_id = mchId;
        nonce_str = nonceStr;
        this.sign = sign;
        this.body = body;
        out_trade_no = outTradeNo;
        total_fee = totalFee;
        spbill_create_ip = spbillCreateIp;
        notify_url = notifyUrl;
        trade_type = tradeType;
        scene_info = sceneInfo;
        this.time_expire = time_expire;
        this.time_start = time_start;
    }
}
