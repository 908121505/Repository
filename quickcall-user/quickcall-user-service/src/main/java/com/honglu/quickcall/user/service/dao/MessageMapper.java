package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.Message;
import org.apache.ibatis.annotations.Param;

/**
 * 消息表Mapper
 *
 * @author duanjun
 * @date 2018-09-22 17:28
 */
public interface MessageMapper {

    /**
     * 插入消息
     *
     * @param record
     * @return
     */
    int insert(Message record);

    /**
     * 根据主键查询消息
     *
     * @param messageId
     * @return
     */
    Message selectByPrimaryKey(Long messageId);

    /**
     * 根据ID更新数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * 查询用户未读消息数量
     *
     * @param receiverId 消息接收者ID
     * @return
     */
    int queryUserUnreadMessageNum(@Param("receiverId") Long receiverId);
}