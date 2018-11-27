package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.exchange.request.InternalMessageRequest;
import com.honglu.quickcall.user.facade.vo.MessageCustomerVO;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.MessageCustomerMapper;
import com.honglu.quickcall.user.service.service.InternalMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xiangxianjin
 * @date: 2018/10/24 22:23
 * @description:
 */
@Service
public class InternalMessageServiceImpl implements InternalMessageService {
    private final static Logger LOGGER = LoggerFactory.getLogger(InternalMessageServiceImpl.class);

    @Autowired
    private MessageCustomerMapper messageCustomerMapper;

    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 查询所有的站内消息
     *
     * @param internalMessageRequest
     * @return
     */
    @Override
    public CommonResponse queryMessages(InternalMessageRequest internalMessageRequest) {
        Integer messageType = internalMessageRequest.getMessageType();
        Long customerId = internalMessageRequest.getCustomerId();
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
        int count = messageCustomerMapper.updateByMessageType(messageType, customerId);
        LOGGER.info("查询站内消息，消息类型：{}，本次获取新的消息数量：{}", messageType, count);
        List<MessageCustomerVO> list = messageCustomerMapper.selectByMessageType(messageType, customerId);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(UserBizReturnCode.Success);
        commonResponse.setMessage(UserBizReturnCode.Success.desc());
        commonResponse.setData(list);
        return commonResponse;
    }
}
