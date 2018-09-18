package com.honglu.quickcall.account.facade.business;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 支付宝相关Dubbo接口
 */
public interface PaymentDubboBusiness {
	CommonResponse excute(AbstractRequest request);
}
