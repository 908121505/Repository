package com.honglu.quickcall.user.facade.business;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 用户dubbo接口
 * @author SteveGuo
 */
public interface UserDubboBusiness{
    CommonResponse excute(AbstractRequest request);
}
