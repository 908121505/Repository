package com.calf.module.system.entity;

import java.util.Date;

public class BizParamType {
	private  String bizParamTypeId ;//'业务参数ID',
	private  String  bizParamTypeName ; // '参数类型名称',
	private  String  bizParamTypeValue ;// '参数类型值',
	private Integer  state ;// '状态:1-启用;2-禁用',
	private Date  createTime ;// '创建时间',
	private String  createMan ;// '创建人',
	private Date  modifyTime ;// '修改时间',
	private  String  modifyMan ;//T '修改人',
	private  String  remark ;// '备注',
	public String getBizParamTypeId() {
		return bizParamTypeId;
	}
	public void setBizParamTypeId(String bizParamTypeId) {
		this.bizParamTypeId = bizParamTypeId;
	}
	public String getBizParamTypeName() {
		return bizParamTypeName;
	}
	public void setBizParamTypeName(String bizParamTypeName) {
		this.bizParamTypeName = bizParamTypeName;
	}
	public String getBizParamTypeValue() {
		return bizParamTypeValue;
	}
	public void setBizParamTypeValue(String bizParamTypeValue) {
		this.bizParamTypeValue = bizParamTypeValue;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyMan() {
		return modifyMan;
	}
	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
