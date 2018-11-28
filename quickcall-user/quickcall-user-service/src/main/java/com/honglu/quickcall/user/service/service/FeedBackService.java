package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.FeedBackInsertRequest;

public interface FeedBackService {

    public CommonResponse insertFeedBack(FeedBackInsertRequest feedBackInsertRequest);
}
