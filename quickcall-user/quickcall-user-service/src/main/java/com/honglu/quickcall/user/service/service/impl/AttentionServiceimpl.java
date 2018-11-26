package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.AttentionCancelRequest;
import com.honglu.quickcall.user.facade.exchange.request.AttentionRequest;
import com.honglu.quickcall.user.service.service.AttentionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 社区帖子关注和取消关注
 *
 * @author 向平
 * @date 2018/11/02
 */
@Service
public class AttentionServiceimpl implements AttentionService {
    private static final Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);

    @Override
    public CommonResponse attention(AttentionRequest params) {
        CommonResponse commonResponse = new CommonResponse();

        return null;
    }

    @Override
    public CommonResponse cancelAttention(AttentionCancelRequest params) {
        CommonResponse commonResponse = new CommonResponse();
//        List<BlacklistVo> customerList = blacklistMapper.selectListByCustomerId(params.getCustomerId());
//        commonResponse.setData(customerList);
//        commonResponse.setCode(UserBizReturnCode.Success);
//        commonResponse.setMessage(UserBizReturnCode.Success.desc());
        return commonResponse;
    }
}
