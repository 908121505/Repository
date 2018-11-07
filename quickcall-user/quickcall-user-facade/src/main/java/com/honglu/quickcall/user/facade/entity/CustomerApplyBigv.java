package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

/**
 * 客户申请大V实体
 *
 * @Auther: duanjun
 * @Date: 2018/11/7 13:47
 */
public class CustomerApplyBigv {
	/**
	 * 主键(15位时间+4位随机数)
	 */
	private Long applyId;
	/**
	 *  关联客户ID
	 */
	private Long customerId;

	/**
	 * 处理状态(0=未联系,1=已联系)
	 */
	private Byte handleStatus;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 修改人
	 */
	private String modifyMan;
	/**
	 * 备注
	 */
	private String remark;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Byte getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(Byte handleStatus) {
		this.handleStatus = handleStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
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