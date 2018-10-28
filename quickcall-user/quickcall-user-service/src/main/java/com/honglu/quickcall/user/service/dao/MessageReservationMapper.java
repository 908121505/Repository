package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.MessageReservation;

import java.util.List;

public interface MessageReservationMapper {
    int insert(MessageReservation record);

    int insertSelective(MessageReservation record);

    MessageReservation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageReservation record);

    int updateByPrimaryKey(MessageReservation record);

    int saveBookingMessage(MessageReservation messageReservation);

    List<MessageReservation> queryBookingMessage(Long id);
}