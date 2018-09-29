package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.Product;
import com.honglu.quickcall.user.facade.entity.in.VProductTag;

public interface ProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    //查询大V上架商品数量
    int queryVProductNum(Long customerId);
    //查询大V上架商品标签信息
    List<VProductTag> selectVProductTag(Long customerId);
    
}