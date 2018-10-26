package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;
import com.honglu.quickcall.user.service.dao.MessageMapper;
import com.honglu.quickcall.user.service.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户消息服务实现类
 *
 * @author duanjun
 * @date 2018-09-22 17:28
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public CommonResponse queryUserUnreadMessageNum(UserUnreadMessageNumRequest params) {
        int num = messageMapper.queryUserUnreadMessageNum(params.getCustomerId());
        return ResultUtils.resultSuccess(num);
    }
}
