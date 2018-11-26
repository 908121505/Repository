package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 弹幕消息请求对象 -- 第二版
 *
 * @author duanjun
 * @date 2018-10-31 17:30:00
 */
public class BarrageMessageV2Request extends AbstractRequest {

    @Override
    public String getBizCode() {
        return AccountFunctionType.GET_BARRAGE_MESSAGE_V2;
    }

}
