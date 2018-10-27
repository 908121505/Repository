package com.calf.module.export.entity;

import org.apache.commons.lang.StringUtils;

public class ExportOrderData {

	private String appid;//项目标识(app_code)，北京统一发放
	private String uid;//用户表示，既用户表（customer_info）id
	private String codes;//义方订单编号（iptv5.0可为空）
	private String product_code;//产品编号
	private String price;//产品价格
	private String begin_time;//订购生效时间
	private String end_time;//订购失效时间
	private String order_time;//订购时间
	private String state;//订购状态
	private String other_codes;//第三方订单编号（iptv5.0可为空）
	private String order_source;//订单来源
	private String remark;//订单所有信息（iptv5.0可为空）
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOther_codes() {
		return other_codes;
	}
	public void setOther_codes(String other_codes) {
		this.other_codes = other_codes;
	}
	public String getOrder_source() {
		return order_source;
	}
	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(end_time))
			end_time = "\\N";
		if(StringUtils.isBlank(begin_time))
			begin_time = "\\N";
		if(StringUtils.isBlank(codes))
			codes = "\\N";
		if(StringUtils.isBlank(other_codes))
			other_codes = "\\N";
		if(StringUtils.isBlank(order_source))
			order_source = "\\N";
		if(StringUtils.isBlank(remark))
			remark = "\\N";
		
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\r\n", 
				uid,appid,codes,product_code,price,begin_time,end_time,order_time,state,other_codes,order_source,remark);
	}
}
