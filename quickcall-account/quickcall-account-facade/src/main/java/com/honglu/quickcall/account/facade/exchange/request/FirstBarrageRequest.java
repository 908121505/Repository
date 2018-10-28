package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 每个用户每天只弹一次窗口对象
 *
 * @author duanjun
 * @date 2018-10-18 16:55
 */
public class FirstBarrageRequest extends AbstractRequest {

    /**客户编号*/
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getBizCode() {
        return AccountFunctionType.FirstOnceWindowEverthDay;
    }

}
