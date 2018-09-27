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
	private static final long serialVersionUID = -3385150802157469404L;



	public static final BizCode paramError=new AccountBizReturnCode("paramError","502",MyServiceCode.ACCOUNT,"参数错误");
    public static final BizCode JdbcError = new AccountBizReturnCode("JdbcError","996",MyServiceCode.ACCOUNT,"操作数据库异常");
    public static final BizCode CheckSignError = new AccountBizReturnCode("CheckSignError","997", MyServiceCode.ACCOUNT,"请求参数验签不正确");
    public static final BizCode SystemParamError = new AccountBizReturnCode("SystemParamError","998", MyServiceCode.ACCOUNT,"系统参数初始化异常");
//    public static final BizCode Unknown = new AccountBizReturnCode("Unknown","999", MyServiceCode.ACCOUNT,"未知异常");
    
    //订单中心使用异常号段：700-799-------开始
    
    public static final BizCode order_ = new AccountBizReturnCode("Unknown","999", MyServiceCode.ACCOUNT,"未知异常");
    
    
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
