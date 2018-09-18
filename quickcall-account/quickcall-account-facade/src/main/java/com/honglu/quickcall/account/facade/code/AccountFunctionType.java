package com.honglu.quickcall.account.facade.code;


/**
 * Created by len.song on 2017-12-07.
 * 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.Account.code()) + "变量"(从 001 - 500 范围)
 */
public interface AccountFunctionType {

    // 从 "002001" 开始， 002保持不变  从 001 开始递增;
    //支付相关业务使用002001 ~ 002100
    String AlipayRecharge = "002001";
    //微信App充值
    String WechatAppRecharge = "002002";
    //支付宝支付回调
    String AlipayNotify = "002003";
    //微信支付回调
    String WechatNotify = "002004";
    //支付宝退款
    String ThirdOrderRefund = "002005";
    //微信H5支付
    String WechatMWEBRecharge = "002006";
    //订单查询
    String ThirdOrderQuery = "002007";
    //支付宝H5支付
    String AlipayWapRecharge = "002008";
    //支付宝批量转账
    String AlipayBatchTransfer = "002009";
    //支付宝单笔转账接口
    String AlipaySingleTransfer = "002010";
    //支付宝批量转账回调
    String AlipayBatchTransferNotify = "002011";
    //支付宝企业账户余额查询
    String AlipayBuinessAcountQuery = "002012";
    //检测是否存在异常订单
    String CheckThirdPlatformOrder = "002013";
    //支付统一下单
    String PayUnifiedOrder = "002014";
    //苹果支付回调订单
    String ApplePayNotifyOrder = "002015";
    





    //后续Code请从002100开始递增
    String GetRecharge = "002100";
    //充值套餐
    String TokenPackage  = "002101";
    //提现记录
    String SelectWithDraw = "002102";
    //申请提现
    String applicationWithDraw = "002103";
    //金豆与人民币比例
    String TokenAsRmb = "002104";
    //查询是否为首冲
    String QueryFirstRecharge= "002105";
    //获取充值金额列表
    String GetRechargeAmountRequest = "002106";
    //通过金额获取充值金额记录
    String GetRechargeByAmount = "002107";


    //账户相关操作从 002200 - 002299
    //创建账户
    String CreateUserAccount = "002200";
    //入账
    String inAccount = "002201";
    //出账
    String outAccount = "002202";
    //冻结账户
    String LockUserAccount = "002203";
    //查询账户
    String QueryAccount = "002204";
    //查询女生分成比例
    String WomanInComeProportion = "002205";
    //创建家族账户
    String createFamilyAccount = "002206";
    //家族入账
    String familyInAccount = "002207";
    //家族出账
    String familyOutAccount = "002208";
    //账户查询
    String queryFamilyAccount = "002209";
    //查询经济家族对象
    String queryEconomyFamilyRules = "002210";

    //订单相关操作 从 002300 - 002399
    //创建订单 传产品
    String CreateOrderWithProduct = "002300";
    //消费
    String PersonConsume = "002301";
    //创建订单 传金额
    String CreateOrderWithMoney = "002302";
    
    //根据产品ID获取产品信息
    String getProductByProductID="002303";

    //支付成功mq 测试
    String PaySuccessMqTest = "002304";
}
