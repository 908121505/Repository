package com.honglu.quickcall.account.web.service;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

/**
 * Created by len.song on 2018-03-06.
 */
public interface AccountCommonService {
    WebResponseModel execute(AbstractRequest request);
}
