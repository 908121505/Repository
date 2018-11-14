package com.honglu.quickcall.account.facade.code;


/**
 * Created by len.song on 2017-12-07.
 * 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.Account.code()) + "变量"(从 001 - 500 范围)
 */
public interface AccountFunctionType {

    // 从 "002001" 开始， 002保持不变  从 001 开始递增;
    //支付相关业务使用002001 ~ 002100
	//支付宝充值
    String AlipayRecharge = "002001";
    //支付宝提现
    String AlipayWhithdraw = "002002";
    //支付宝支付回调
    String AlipayNotify = "002003";
    //微信支付回调
    String WechatNotify = "002004";
    //绑定支付宝
    String BindAliaccount= "002005";
    //苹果内购充值
    String APPLY_PAY_RECHARGE = "002006";
    //苹果内购回调验证
    String APPLY_PAY_NOTIFY = "002007";

   

    //账户相关操作从 002200 - 002299
    //创建账户
    String CreateUserAccount = "002200";
    //每个用户每天只弹一次窗口
    String FirstOnceWindowEverthDay = "022202";
    //入账
    String InAccount  ="002201";
    //出账
    String OutAccount  ="002202";
    //查询账户
    String QueryAccount = "002203";
    

    //订单相关操作 从 002300 - 002399
    //创建订单 传产品
    
    //支付成功mq 测试
    String PaySuccessMqTest = "002304";

    // 获取弹幕消息
    String GET_BARRAGE_MESSAGE = "002305";
    // 获取弹幕消息 -- 第二版
    String GET_BARRAGE_MESSAGE_V2 = "002306";
    
    /**查询渠道开关**/
    String  CHANNEL_SWITCH_STATUS = "002307";
    /**
     * 查询是否是第一次充值
     */
    String  ISFIRSTRECHARGE = "002308";
}
