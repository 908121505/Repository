package com.calf.module.customer.vo;

import java.util.Date;

/**
 * 客户申请大V VO
 *
 * @Auther: Sunju
 * @Date: 2018/11/7 13:47
 */
public class CustomerApplyBigvVO {
	// 主键(15位时间+4位随机数)
	private Long applyId;
	// appId
	private String appId;
	// 用户昵称
	private String nickName;
	// 手机
	private Long phone;
	// 关联客户ID
	private Long customerId;
	//处理状态(0=未联系,1=已联系)
	private Byte handleStatus;
	//申请时间
	private String applyTime;
	//处理人
	private String handleMan;
	//处理时间
	private String handleTime;
	//修改人
	private String modifyMan;
	//修改时间
	private String modifyTime;
	//备注
	private String remark;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
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

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getHandleMan() {
		return handleMan;
	}

	public void setHandleMan(String handleMan) {
		this.handleMan = handleMan;
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
}