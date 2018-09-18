package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class GetProductByProductIdRequest extends AbstractRequest{
	private String product_id;

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.getProductByProductID;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

}
