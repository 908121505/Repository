package com.honglu.quickcall.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.Product;
import com.honglu.quickcall.account.facade.vo.FirstPageDaVinfoVO;
import com.honglu.quickcall.account.facade.vo.OrderDaVProductVO;

public interface ProductMapper {

    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);


    Product selectByPrimaryKey(Long productId);


    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> selectListBySkillIdList(@Param("customerId")  Long customerId,@Param("list")  List<Long> skillIdList);

	List<FirstPageDaVinfoVO> selectTotalDaVProduct(@Param("customerId")  Long customerId,@Param("skillId")  Long skillId,@Param("pageIndex")  Integer pageIndex,@Param("pageSize")  Integer pageSize);

	
	List<OrderDaVProductVO> selectDaVPersonalProduct(@Param("customerId") Long customerId);


	
	
	
	
}