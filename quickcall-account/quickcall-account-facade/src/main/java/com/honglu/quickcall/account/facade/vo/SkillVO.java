package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.List;

public class SkillVO {
    
    private Long skillId;
    private Long  productId;
    private Integer  productStatus;
    private String name;
    /**价格列表*/
    private List<BigDecimal>  priceList;
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BigDecimal> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<BigDecimal> priceList) {
		this.priceList = priceList;
	}
    
    
    
    
   
}