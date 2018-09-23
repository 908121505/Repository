package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Product;
import com.honglu.quickcall.account.facade.entity.Skill;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageDaVinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageSkillinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.facade.vo.SkillVO;
import com.honglu.quickcall.account.service.dao.ProductMapper;
import com.honglu.quickcall.account.service.dao.SkillMapper;
import com.honglu.quickcall.account.service.service.SkillService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能管理中心
 * 
 * @Package: com.honglu.quickcall.account.web.service.impl
 * @author: chenliuguang
 * @date: 2018年9月22日 下午3:17:04
 */
@Service
public class SkillServiceImpl implements SkillService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SkillServiceImpl.class);
	
	@Autowired
	private SkillMapper  skillMapper;
	@Autowired
	private ProductMapper  productMapper;

	@Override
	public CommonResponse querySkillInfoPersonal(SkillInfoRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "创建账户参数异常");
		}
		LOGGER.info("用户编号为：" + request.getCustomerId() + "的用户开始创建账户...");
		Long  customerId =  request.getCustomerId();
		//首先查询所有的技能信息
		
		List<Skill>   skillList = skillMapper.selectTotalSkill();
		List<Long>  skillIdList =  new ArrayList<Long>();
		List<SkillVO>   resultList =  new  ArrayList<SkillVO>();
		if(CollectionUtils.isEmpty(skillList)){
			//结果为空直接返回
		}else{
			
			
			for (Skill skill : skillList) {
				SkillVO  vo =  new SkillVO();
				Long  skillId =  skill.getId();
				skillIdList.add(skillId);
				vo.setName(skill.getName());
				vo.setProductStatus(OrderSkillConstants.PRODUCT_STATUS_UN_EFFECTIVE);
				vo.setSkillId(skillId);
				vo.setPriceList(getPriceList(skill));
				resultList.add(vo);
				
			}
			//
			List<Product>  prodList = productMapper.selectListBySkillIdList(customerId ,skillIdList);
			//查询结果为空，则该用户没有一个技能设置
			if(CollectionUtils.isEmpty(prodList)){
				
			}else{
				//查询结果不为空，则该用户已经设置了相关的技能
				Map<Long, Product>  prodMap  = new HashMap<Long, Product>();
				for (Product product : prodList) {
					Long  pSkillId =  product.getSkillId();
					prodMap.put(pSkillId, product);
					
				}
				for (SkillVO skillVO : resultList) {
					Long  skillId =  skillVO.getSkillId();
					Product  product = prodMap.get(skillId);
					skillVO.setProductId(product.getProductId());
					skillVO.setProductStatus(product.getProductStatus());
				}
			}
		}
		
		 CommonResponse commonResponse = new CommonResponse();
		 commonResponse.setCode(BizCode.Success);
		 commonResponse.setMessage(BizCode.Success.desc());
		 commonResponse.setData(resultList);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	
	

	
	

	
	
	

	@Override
	public CommonResponse updateSkillInfoPersonal(SkillUpdateRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "创建账户参数异常");
		}
		LOGGER.info("用户编号为：" + request.getCustomerId() + "的用户开始创建账户...");
		Long  customerId =  request.getCustomerId();
		//查询技能信息直接更新吧
		Long  productId =  request.getProductId();
		
		Long skillId =  request.getSkillId();
		BigDecimal  price = request.getPrice();
		if(productId == null){
			Product record = new Product();
			record.setProductId(productId);
			record.setProductStatus(request.getProductStatus());
			record.setPrice(price);
			record.setSkillId(skillId);
			record.setSellerId(customerId);
			record.setCreateTime(new Date());
			//直接插入
			productMapper.insertSelective(record );
		}else{
			Product record  = new Product();
			record.setProductId(productId);
			record.setProductStatus(request.getProductStatus());
			record.setPrice(request.getPrice());
			record.setSkillId(skillId);
			record.setSellerId(customerId);
			record.setModifyTime(new Date());
			//直接更新
			productMapper.updateByPrimaryKeySelective(record);
		}
		 CommonResponse commonResponse = new CommonResponse();
		 commonResponse.setCode(BizCode.Success);
		 commonResponse.setMessage(BizCode.Success.desc());
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	

	@Override
	public CommonResponse getFirstPageDaVinfo(FirstPageDaVinfoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CommonResponse getFirstPageSkillinfo(FirstPageSkillinfoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	/**
	 * 根据技能信息获取技能价格列表
	 * @param skill
	 * @return
	 */
	public List<BigDecimal>   getPriceList(Skill  skill){
		
		List<BigDecimal>   retList =  new  ArrayList<BigDecimal>();
		BigDecimal  maxPrice = skill.getMaxPrice();
		BigDecimal  minPrice = skill.getMinPrice();
		BigDecimal  priceStep = skill.getPriceStep();
		
		retList.add(minPrice);
		BigDecimal   tempPrice =  minPrice;
		for (;;) {
			tempPrice =  tempPrice.add(priceStep);
			if(tempPrice.compareTo(maxPrice) <= 0){
				retList.add(tempPrice);
			}else{
				break;
			}
		}
		
		return retList ;
	}










	
//	public static void main(String[] args) {
//	BigDecimal  maxPrice = new BigDecimal(109);
//	BigDecimal  minPrice = new BigDecimal(10);
//	BigDecimal  priceStep = new BigDecimal(10);
//	
//	BigDecimal   tempPrice =  minPrice;
//	System.out.println("============="+tempPrice);
//	for (;;) {
//		tempPrice =  tempPrice.add(priceStep);
//		if(tempPrice.compareTo(maxPrice) <= 0){
//			System.out.println("============="+tempPrice);
//		}else{
//			break;
//		}
//	}
//}

}
