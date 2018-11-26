package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.FeedBack;
import com.honglu.quickcall.user.facade.exchange.request.FeedBackInsertRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.FeedBackMapper;
import com.honglu.quickcall.user.service.service.FeedBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.util.Strings;

import java.util.Date;

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    FeedBackMapper feedBackMapper;

    @Autowired
    private CustomerMapper customerMapper;
    private Logger logger = LoggerFactory.getLogger(FeedBackService.class);


    @Override
    public CommonResponse insertFeedBack(FeedBackInsertRequest feedBackInsertRequest) {

        Customer customer = customerMapper.selectByPrimaryKey(Long.valueOf(feedBackInsertRequest.getCustomerId()));
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		} 
        String customerId = feedBackInsertRequest.getCustomerId();
        String feedBackContent = feedBackInsertRequest.getFeedBackContent();
        String contactWay = feedBackInsertRequest.getContactWay();
        FeedBack feedBack = new FeedBack();
        feedBack.setCreateTime(new Date());
        feedBack.setContactWay(contactWay);
        feedBack.setFeedBackContent(feedBackContent);
        feedBack.setCustomerId(Long.valueOf(customerId));
        feedBack.setCreateMan(customerId);
        logger.info("enter data into database table feed_back:{}",feedBack);
        feedBackMapper.insertFeedBack(feedBack);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(UserBizReturnCode.Success);
        commonResponse.setMessage(UserBizReturnCode.Success.desc());
        return commonResponse;
    }
}
