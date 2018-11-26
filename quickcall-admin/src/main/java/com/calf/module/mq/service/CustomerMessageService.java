package com.calf.module.mq.service;

/**
 * @author: xiangxianjin
 * @date: 2018/10/24 17:39
 * @description:
 */
public interface CustomerMessageService {

    /**
     * 给所有用户发送站内消息
     * @param messageId 站内消息编号
     */
    void sendInternalMessage(String messageId);
}
