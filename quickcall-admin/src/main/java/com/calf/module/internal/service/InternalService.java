package com.calf.module.internal.service;

import com.calf.module.internal.entity.Message;

/**
 * @author: xiangxianjin
 * @date: 2018/10/25 16:52
 * @description:
 */
public interface InternalService {

    /**
     * 新增
     * @param entity
     * @return
     */
    int addMessage(Message entity);

    /**
     * 新增
     * @param entity
     * @return
     */
    int updateMessage(Message entity);

    /**
     * 发送消息
     * @param messageId
     */
    int sendMessage(String messageId);

}
