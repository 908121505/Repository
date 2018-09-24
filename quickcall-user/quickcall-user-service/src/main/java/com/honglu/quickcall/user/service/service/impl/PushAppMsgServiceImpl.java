package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.exchange.request.PushAppMsgRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.MessageMapper;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.PushAppMsgService;
import com.honglu.quickcall.user.service.util.GtPushUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 推送APP消息服务实现类
 *
 * @author duanjun
 * @date 2018-09-23 17:25
 */
@Service
public class PushAppMsgServiceImpl implements PushAppMsgService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CommonResponse pushMsg(PushAppMsgRequest request) {
        // 查询要推送的用户信息
        Customer receiveCustomer = customerMapper.selectByPrimaryKey(request.getReceiverId());
        if (receiveCustomer == null) {
            return ResultUtils.resultDataNotExist("用户不存在");
        }

        if (StringUtils.isBlank(receiveCustomer.getDeviceId())) {
            return ResultUtils.resultParamEmpty("用户设备为空");
        }

        // 先把消息插入数据库
        Message message = new Message();
        message.setMessageId(UUIDUtils.getId());
        message.setType(request.getMsgType().getType().byteValue());
        message.setMessageContent(request.getMsgType().getMsgContent());
        message.setSenderId(request.getSenderId());
        message.setReceiverId(request.getReceiverId());
        message.setMessageStatus(Byte.valueOf("1"));
        // 插入成功
        if (messageMapper.insert(message) <= 0) {
            return ResultUtils.result(BizCode.DBError, "数据库插入失败");
        }

        GtPushUtil.pushMessage(Arrays.asList(receiveCustomer.getDeviceId()), request.getMsgType().getMsgContent(), "");

        return ResultUtils.resultSuccess();
    }

}
