package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;

public interface PersonInfoService {
	/**
     * 查看个人资料
     * @param request
     * @return
     */
    CommonResponse queryPersonInfo(PersonInfoRequest request);

}
