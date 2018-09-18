package com.honglu.quickcall.account.web.service;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

/**
 * Created by len.song on 2017-12-19.
 */
public interface AccountOrderService {
    WebResponseModel execute(AbstractRequest request);
}
