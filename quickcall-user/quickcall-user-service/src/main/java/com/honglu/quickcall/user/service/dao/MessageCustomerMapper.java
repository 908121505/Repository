package com.honglu.quickcall.user.service.dao;


import com.honglu.quickcall.user.facade.vo.MessageCustomerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xiangxianjin
 * @date: 2018/10/24 22:23
 * @description:
 */
public interface MessageCustomerMapper {

    /**
     * 查询站内信消息
     * @param messageType   消息类型
     * @param customerId    客户编号
     * @return
     */
    List<MessageCustomerVO> selectByMessageType(@Param("messageType")Integer messageType, @Param("customerId")Long customerId);

    /**
     * 更新消息为已读
     * @param messageType   消息类型
     * @param customerId    客户编号
     * @return
     */
    int updateByMessageType(@Param("messageType")Integer messageType, @Param("customerId")Long customerId);

}