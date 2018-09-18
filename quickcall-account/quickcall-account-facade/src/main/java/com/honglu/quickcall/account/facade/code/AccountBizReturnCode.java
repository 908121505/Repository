package com.honglu.quickcall.account.facade.code;

import com.honglu.quickcall.common.api.code.AbstractEnum;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.ServiceCode;

/**
 * Created by len.song on 2017-12-07.
 * 异常错误编码
 */
public class AccountBizReturnCode extends BizCode {
    //异常编码(501 - 999)


    public static final BizCode TradeError=new AccountBizReturnCode("","501",MyServiceCode.ACCOUNT,"交易异常");
    public static final BizCode paramError=new AccountBizReturnCode("paramError","535",MyServiceCode.ACCOUNT,"参数错误");

    public static final BizCode UserIsNotExists = new AccountBizReturnCode("UserIsNotExists","600",MyServiceCode.ACCOUNT,"用户不存在");
    public static final BizCode UserIdCantBeNull = new AccountBizReturnCode("UserIdCantBeNull","601",MyServiceCode.ACCOUNT,"用户id不能为空");
    public static final BizCode AccountOperaterTypeError = new AccountBizReturnCode("AccountOperaterTypeError","602",MyServiceCode.ACCOUNT,"账户流水操作类型异常");
    public static final BizCode AccountBusinessTypeError = new AccountBizReturnCode("AccountBusinessTypeError","603",MyServiceCode.ACCOUNT,"账户流水业务类型异常");
    public static final BizCode AccountMoneyError = new AccountBizReturnCode("AccountMoneyError","604",MyServiceCode.ACCOUNT,"操作账户金额不能为负数");
    public static final BizCode BalanceOfAccountLack = new AccountBizReturnCode("BalanceOfAccountLack","605",MyServiceCode.ACCOUNT,"账户余额不足");
    public static final BizCode UserAccountIsNull = new AccountBizReturnCode("UserAccountIsNull","606",MyServiceCode.ACCOUNT,"用户账户为空");
    public static final BizCode UserAccountLocked = new AccountBizReturnCode("UserAccountLocked","607",MyServiceCode.ACCOUNT,"账户被锁定，不能进行操作");
    public static final BizCode UserAccountIsBusy = new AccountBizReturnCode("UserAccountIsBusy","608",MyServiceCode.ACCOUNT,"账户繁忙，请稍后再试...");
    public static final BizCode NOExistError = new AccountBizReturnCode("NOExistError","611",MyServiceCode.ACCOUNT,"支付宝绑定关系不存在");
    public static final BizCode NOWithdrawError = new AccountBizReturnCode("NOWithdrawError","612",MyServiceCode.ACCOUNT,"提现次数不足");
    public static final BizCode JdbcError = new AccountBizReturnCode("JdbcError","613",MyServiceCode.ACCOUNT,"操作数据库异常");


    public static final BizCode ConsumeSexError =new AccountBizReturnCode("ConsumeSexError","615",MyServiceCode.ACCOUNT,"消费订单性别异常");


    public static final BizCode OrderMoneyError = new AccountBizReturnCode("OrderMoneyError","614",MyServiceCode.ACCOUNT,"订单金额异常");
    public static final BizCode OrderNotExist = new AccountBizReturnCode("OrderNotExist","616",MyServiceCode.ACCOUNT,"订单不存在");
    public static final BizCode OrderNotPay = new AccountBizReturnCode("OrderNotPay","617",MyServiceCode.ACCOUNT,"订单未支付");
    public static final BizCode OrderTradeClose = new AccountBizReturnCode("OrderTradeClose","618",MyServiceCode.ACCOUNT,"订单交易已关闭");
    public static final BizCode LockBalanceOfAccountLack = new AccountBizReturnCode("LockBalanceOfAccountLack","619",MyServiceCode.ACCOUNT,"冻结金额不足");


    //家族账户操作 630 - 650
    public static final BizCode FamilyNotExists = new AccountBizReturnCode("FamilyNotExists","630",MyServiceCode.ACCOUNT,"家族编号不能为空");
    public static final BizCode FamilyAccountNotExists = new AccountBizReturnCode("FamilyAccountNotExists","631",MyServiceCode.ACCOUNT,"家族账户为空，不能进行操作");


    public static final BizCode CheckSignError = new AccountBizReturnCode("CheckSignError","997", MyServiceCode.ACCOUNT,"请求参数验签不正确");
    public static final BizCode SystemParamError = new AccountBizReturnCode("SystemParamError","998", MyServiceCode.ACCOUNT,"系统参数初始化异常");
    public static final BizCode Unknown = new AccountBizReturnCode("Unknown","999", MyServiceCode.ACCOUNT,"未知异常");


    public AccountBizReturnCode() {

    }

    public AccountBizReturnCode(String name, String code, ServiceCode serviceCode, String desc) {
        super(name, code, serviceCode, desc);
    }

    @Override
    protected Class<? extends AbstractEnum> getEnumType() {
        return AccountBizReturnCode.class;
    }
}
