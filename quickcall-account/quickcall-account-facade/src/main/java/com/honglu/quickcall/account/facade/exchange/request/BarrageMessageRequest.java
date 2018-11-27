package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.math.BigDecimal;

/**
 * 弹幕消息请求对象
 *
 * @author duanjun
 * @date 2018-10-18 16:55
 */
public class BarrageMessageRequest extends AbstractRequest {

    @Override
    public String getBizCode() {
        return AccountFunctionType.GET_BARRAGE_MESSAGE;
    }

}
