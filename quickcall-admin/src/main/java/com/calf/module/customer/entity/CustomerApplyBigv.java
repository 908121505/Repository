package com.calf.module.customer.entity;

import java.util.Date;

/**
 * 客户申请大V实体
 *
 * @Auther: Sunju
 * @Date: 2018/11/7 13:47
 */
public class CustomerApplyBigv {
	// 主键(15位时间+4位随机数)
	private String applyId;
	// 关联客户ID
	private String customerId;
	//处理状态(0=未联系,1=已联系)
	private Byte handleStatus;
	//申请时间
	private String applyTime;
	//处理人
	private String handleUser;
	//处理时间
	private String handleTime;
	//修改人
	private String modifyMan;
	//修改时间
	private String modifyTime;
	//备注
	private String remark;
	//原处理状态
	private String oldHandleStatus;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Byte getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(Byte handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getHandleUser() {
		return handleUser;
	}

	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOldHandleStatus() {
		return oldHandleStatus;
	}

	public void setOldHandleStatus(String oldHandleStatus) {
		this.oldHandleStatus = oldHandleStatus;
	}
}