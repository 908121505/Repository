package com.calf.module.order.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：主播技能信息
 * @Package: com.calf.module.order.resp
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午7:18:15
 */
public class ProductVO {
   
	
    private String name;
    private BigDecimal price;
    private Integer serviceTime;
    private Integer productStatus;
    private String servNickName;
    private Date createTime;
    private Date modifyTime;
    
	public String getServNickName() {
		return servNickName;
	}


	public void setServNickName(String servNickName) {
		this.servNickName = servNickName;
	}


	public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public Integer getServiceTime() {
        return serviceTime;
    }

   
    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    
    public BigDecimal getPrice() {
        return price;
    }

    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

   
   

   
    public Integer getProductStatus() {
        return productStatus;
    }

    
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    
   
    
    public Date getCreateTime() {
        return createTime;
    }

   
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    public Date getModifyTime() {
        return modifyTime;
    }

   
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    
   
    
    
}