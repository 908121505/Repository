package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V个人产品
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午3:39:33 
 */
public class OrderDaVProductVO implements  Serializable{

	private static final long serialVersionUID = 7332266848398061590L;
	/**主播ID*/
	private Long  sellerId;
	/**主播昵称*/
	private String  nickName;
	/**主播头像*/
	private String  headPortraitUrl;
	/**产品ID*/
	private Long  productId; 
	/**主播昵称*/
	private String productName ;
	
	private BigDecimal  price;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	
	
}
