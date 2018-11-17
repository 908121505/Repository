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


	private static final long serialVersionUID = 4589118238396417922L;
	public static final BizCode paramError=new AccountBizReturnCode("paramError","502",MyServiceCode.ACCOUNT,"参数错误");
    public static final BizCode JdbcError = new AccountBizReturnCode("JdbcError","996",MyServiceCode.ACCOUNT,"操作数据库异常");
    public static final BizCode CheckSignError = new AccountBizReturnCode("CheckSignError","997", MyServiceCode.ACCOUNT,"请求参数验签不正确");
    public static final BizCode SystemParamError = new AccountBizReturnCode("SystemParamError","998", MyServiceCode.ACCOUNT,"系统参数初始化异常");
    public static final BizCode Unknown = new AccountBizReturnCode("Unknown","999", MyServiceCode.ACCOUNT,"未知异常");
    
    //订单中心使用异常号段：700-799-------开始
    /**订单不存在*/
    public static final BizCode ORDER_NOT_EXIST = new AccountBizReturnCode("ORDER_NOT_EXIST","700", MyServiceCode.ACCOUNT,"订单状态不符合");
    /**订单状态不符合*/
    public static final BizCode ORDER_STATUS_ERROR = new AccountBizReturnCode("ORDER_CANCEL_CODE","701", MyServiceCode.ACCOUNT,"订单状态不符合");
    /**订单双方同时出发服务完成*/
    public static final BizCode ORDER_FINISH_ERROR = new AccountBizReturnCode("ORDER_FINISH_ERROR","711", MyServiceCode.ACCOUNT,"请稍后重试");
    /**订单双方同时出发服务完成*/
    public static final BizCode ORDER_CANCEL_ERROR = new AccountBizReturnCode("ORDER_CANCEL_ERROR","714", MyServiceCode.ACCOUNT,"请稍后重试");
    /**下单提示*/
    public static final BizCode ORDER_SAVE_ERROR = new AccountBizReturnCode("ORDER_SAVE_ERROR","713", MyServiceCode.ACCOUNT,"请稍后重试");
    /**用户同意声优服务完成*/
    public static final BizCode ORDER_CONFIRM_FINISH_ERROR = new AccountBizReturnCode("ORDER_CONFIRM_FINISH_ERROR","714", MyServiceCode.ACCOUNT,"请稍后重试");
    
    public static final BizCode ACCOUNT_NOT_EXIST = new AccountBizReturnCode("ACCOUNT_NOT_EXIST","712", MyServiceCode.ACCOUNT,"请稍后重试");
    /**余额不足*/
    public static final BizCode ORDER_PAY_BALANCE_NOT_ENOUGH = new AccountBizReturnCode("ORDER_PAY_BALANCE_NOT_ENOUGH","702", MyServiceCode.ACCOUNT,"订单状态不符合");
    /**账户不存在*/
    public static final BizCode ORDER_PAY_ACCOUNT_NOT_EXIST = new AccountBizReturnCode("ORDER_PAY_ACCOUNT_NOT_EXIST","703", MyServiceCode.ACCOUNT,"订单状态不符合");
    /**余额不足*/
    public static final BizCode DV_BUSYING = new AccountBizReturnCode("DV_BUSYING","704", MyServiceCode.ACCOUNT,"大V忙");
    
    
    //订单中心使用异常号段：700-799-------结束
    


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
