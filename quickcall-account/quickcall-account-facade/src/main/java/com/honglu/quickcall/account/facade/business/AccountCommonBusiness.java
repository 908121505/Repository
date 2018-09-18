package com.honglu.quickcall.account.facade.business;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * Created by len.song on 2018-03-06.
 */
public interface AccountCommonBusiness {
    CommonResponse excute(AbstractRequest request);
}
