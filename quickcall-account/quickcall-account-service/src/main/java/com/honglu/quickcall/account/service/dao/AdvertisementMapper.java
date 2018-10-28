package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.Advertisement;

public interface AdvertisementMapper {
    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    Advertisement selectAdvertisement();

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}