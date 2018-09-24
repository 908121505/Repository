package com.honglu.quickcall.account.service.dao;

import java.util.List;

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

	List<Product> selectListBySkillIdList(Long customerId, List<Long> skillIdList);

	List<FirstPageDaVinfoVO> selectTotalDaVProduct(Long skillId);

	
	List<OrderDaVProductVO> selectDaVPersonalProduct(Long customerId);


	
	
	
	
}