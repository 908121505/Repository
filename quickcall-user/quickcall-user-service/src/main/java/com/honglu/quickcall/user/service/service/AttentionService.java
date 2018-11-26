package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.AttentionCancelRequest;
import com.honglu.quickcall.user.facade.exchange.request.AttentionRequest;

/**
 * Created by cp on 2018/10/21.
 */
public interface AttentionService {
    /**
     * 关注
     * @param params
     * @return
     */
    CommonResponse attention(AttentionRequest params);

    /**
     * 取消关注
     * @param params
     * @return
     */
    CommonResponse cancelAttention(AttentionCancelRequest params);
}
