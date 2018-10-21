package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.QueryBlacklistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.RemoveBlacklistReq;

/**
 * Created by cp on 2018/10/21.
 */
public interface BlacklistService {

    /**
     * 删除黑名单
     * @param params
     * @return
     */
    CommonResponse removeBlacklist(RemoveBlacklistReq params);
    /**
     * 查询黑名单
     * @param params
     * @return
     */
    CommonResponse queryBlacklist(QueryBlacklistReq params);

}
