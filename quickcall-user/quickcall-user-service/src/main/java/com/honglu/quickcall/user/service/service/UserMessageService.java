package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageQueryRequest;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageSaveRequest;
import com.honglu.quickcall.user.facade.exchange.request.CustomerMessageSettingQueryRequest;
import com.honglu.quickcall.user.facade.exchange.request.CustomerMsgSettingRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;

/**
 * 用户消息服务接口
 *
 * @author duanjun
 * @date 2018-09-22 17:26
 */
public interface UserMessageService {

    /**
     * 查询用户未读消息数量
     *
     * @param params 请求参数
     * @return
     */
    CommonResponse queryUserUnreadMessageNum(UserUnreadMessageNumRequest params);

    /**
     * 保存预约信息
     *
     * @param params 请求参数
     * @return
     */
    CommonResponse saveBookingMessage(BookingMessageSaveRequest params);

    /**
     * 获取用户预约消息
     *
     * @param params 请求参数
     * @return
     */
    CommonResponse queryBookingMessage(BookingMessageQueryRequest params);
    
    /**
     * 获取用户聊天消息权限设置
     * @param params 请求参数
     * @return
     */
    CommonResponse queryCustomerMessageSetting(CustomerMessageSettingQueryRequest params);
    
    /**
     * 用户聊天消息权限设置信息保存
     * @param params
     * @return
     */
	CommonResponse saveCustomerMessageSetting(CustomerMsgSettingRequest params);

}
