package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerInterest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerInterestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerInterest record);

    int insertSelective(CustomerInterest record);

    CustomerInterest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerInterest record);

    int updateByPrimaryKey(CustomerInterest record);

    /**
     * 查询数据是否存在，防止重复提交  customerId  interestId
     *
     * @param record
     * @return
     */
    int selectRepetitiveData(CustomerInterest record);

    /**
     * 根据customerId更新
     *
     * @param record
     * @return
     */
    int updateByCustomerIdSelective(CustomerInterest record);

    /**
     * 根据客户ID删除
     *
     * @param customerId
     * @return
     */
    int deleteByCustomerId(Long customerId);

    /**
     * 查询用户兴趣
     *
     * @param customerId
     * @return
     */
    List<String> queryCustomerInterestList(@Param("customerId") Long customerId);
}