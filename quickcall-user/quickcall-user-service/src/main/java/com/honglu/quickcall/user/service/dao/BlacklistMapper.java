package com.honglu.quickcall.user.service.dao;


import com.honglu.quickcall.user.facade.entity.Blacklist;
import com.honglu.quickcall.user.facade.vo.BlacklistVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlacklistMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blacklist
     *
     * @mbggenerated Sun Oct 21 19:35:51 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blacklist
     *
     * @mbggenerated Sun Oct 21 19:35:51 CST 2018
     */
    int insert(Blacklist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blacklist
     *
     * @mbggenerated Sun Oct 21 19:35:51 CST 2018
     */
    int insertSelective(Blacklist record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blacklist
     *
     * @mbggenerated Sun Oct 21 19:35:51 CST 2018
     */
    Blacklist selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blacklist
     *
     * @mbggenerated Sun Oct 21 19:35:51 CST 2018
     */
    int updateByPrimaryKeySelective(Blacklist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blacklist
     *
     * @mbggenerated Sun Oct 21 19:35:51 CST 2018
     */
    int updateByPrimaryKey(Blacklist record);

    List<BlacklistVo> selectListByCustomerId(@Param("customerId") Long customerId);

    int selectCountByCusIdAndBlackCusId(@Param("customerId") Long customerId, @Param("blackCustomerId") Long blackCustomerId);

    /**
     * 判断声音是否被拉黑
     * @param customerId -- 客户ID
     * @param blackCustomerId -- 被拉黑的客户ID
     * @return 0=未拉黑；1=已拉黑
     */
    Integer judgeCustomerIfBacked(@Param("customerId") Long customerId, @Param("blackCustomerId") Long blackCustomerId);

    int updateStatusById(@Param("customerId") Long customerId, @Param("blackCustomerId") Long blackCustomerId);
}