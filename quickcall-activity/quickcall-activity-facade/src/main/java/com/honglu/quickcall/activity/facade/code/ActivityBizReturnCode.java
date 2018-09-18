package com.honglu.quickcall.activity.facade.code;

import com.honglu.quickcall.common.api.code.AbstractEnum;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.ServiceCode;

/**
 * Created by len.song on 2017-12-07.
 * 异常错误编码
 */
public class ActivityBizReturnCode extends BizCode {
    //异常编码(501 - 999)


    public static final BizCode Unknown = new ActivityBizReturnCode("Unknown","999", MyServiceCode.ACTIVITY,"未知异常");
    public static final BizCode BizFunctionTypeNotMatch=new ActivityBizReturnCode("BizFunctionTypeNotMatch","998",MyServiceCode.ACTIVITY,"接口功能编码不匹配");
    
    public ActivityBizReturnCode() {

    }

    public ActivityBizReturnCode(String name, String code, ServiceCode serviceCode, String desc) {
        super(name, code, serviceCode, desc);
    }

    @Override
    protected Class<? extends AbstractEnum> getEnumType() {
        return ActivityBizReturnCode.class;
    }
}
