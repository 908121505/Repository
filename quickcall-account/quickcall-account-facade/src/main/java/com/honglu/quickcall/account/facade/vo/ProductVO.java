package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.List;

public class ProductVO {
    
    private String productId;
    
    private String name;
   
    private Integer serviceTime;

    private BigDecimal price;

    private Integer type;

    private Integer productStatus;

    
    private Long sellerId;
    
    /**价格列表*/
    private List<BigDecimal>  priceList;



    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public List<BigDecimal> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<BigDecimal> priceList) {
		this.priceList = priceList;
	}
	
	
   
}