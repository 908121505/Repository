package com.honglu.quickcall.task.dao;

import java.util.List;

import com.honglu.quickcall.task.entity.FadeCustomer;
import com.honglu.quickcall.task.entity.example.FadeCustomerExample;
import org.apache.ibatis.annotations.Param;

public interface FadeCustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int countByExample(FadeCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int deleteByExample(FadeCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int insert(FadeCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int insertSelective(FadeCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    List<FadeCustomer> selectByExample(FadeCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    FadeCustomer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int updateByExampleSelective(@Param("record") FadeCustomer record, @Param("example") FadeCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int updateByExample(@Param("record") FadeCustomer record, @Param("example") FadeCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int updateByPrimaryKeySelective(FadeCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fade_customer
     *
     * @mbggenerated Thu Oct 18 15:42:16 CST 2018
     */
    int updateByPrimaryKey(FadeCustomer record);

    /**
     * 跳过 index 条，查询一条数据
     * @param index
     * @return
     */
    FadeCustomer selectOneSkipNum(@Param("index") int index);
}