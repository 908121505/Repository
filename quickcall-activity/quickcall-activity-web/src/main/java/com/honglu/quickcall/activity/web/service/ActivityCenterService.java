package com.honglu.quickcall.activity.web.service;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

/**
 * Created by len.song on 2017-12-08.
 */
public interface ActivityCenterService {
    WebResponseModel execute(AbstractRequest request);
}
